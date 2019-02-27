package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.User;
import pl.coderslab.model.UserSession;
import pl.coderslab.service.QuestionService;
import pl.coderslab.service.UserService;
import pl.coderslab.utils.BCrypt;
import pl.coderslab.validator.RegistrationValidator;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes({"questionNumber", "size", "points", "goodAnswers", "loggedUser", "firstName"})
public class HomeController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;


    @Autowired
    private UserSession userSession;

    @RequestMapping("")
    public String home(Model model) {

        questionService.startSetting(model);
        model.addAttribute("userSession", userSession);


        return "home";
    }

    @RequestMapping("/course/{number}")
    public String course(@PathVariable int number, Model model) {
        if (number < 1) {
            number = 1;
        }
        if (number > 37) {
            number = 37;
        }
        model.addAttribute("numberImage", number);
        model.addAttribute("userSession", userSession);

        return "/course";
    }

    // registration user
    @GetMapping("/registration")
    public String registrationUser(Model model) {
        model.addAttribute("user", new User());
        return "/users/registration";
    }

    @PostMapping("/registration")
    public String registrationUser(@Validated(RegistrationValidator.class) User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/users/registration";
        }
        String check = userService.checkRegistration(user);
        if (!"registrationSucces".equals(check)) {
            model.addAttribute("invalid", true);
            model.addAttribute("message", check);
            return "/users/registration";
        }
        String passToHash = user.getPassword(); // hash password
        user.setPassword(BCrypt.hashpw(passToHash, BCrypt.gensalt()));

        userService.save(user);
        model.addAttribute("registration", true);
        model.addAttribute("message", "Dziękujemy za rejestrację. Teraz możesz się zalogować.");
        return "/home";
    }

    // login
    @GetMapping("/login")
    public String login() {

        return "/login";
    }

    @PostMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        String check = userService.checkLogin(login, pass, model);
        if (!"loginSucces".equals(check)) {
            request.setAttribute("login", login); //, by nie musieć wpisywać znowu
            request.setAttribute("pass", pass);
            return "/login";
        }
        model.addAttribute("loggedUser", true);
        userService.sessionStart(login); /////
        model.addAttribute("firstName", userSession.getUserInSession().getFirstName());
        model.addAttribute("registration", true);
        model.addAttribute("message", "Zalogowałeś się. Zapoznaj się z materiałami szkoleniowymi, wykonaj test.");
        return "/home";

    }

    @RequestMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("loggedUser", false);

        userSession.setLoggedUser(false);
        userSession.setUserInSession(null);
        return "/home";
    }

}
