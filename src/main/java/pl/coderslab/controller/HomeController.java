package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.User;
import pl.coderslab.model.UserSession;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.RegistrationValidator;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes({"questionNumber", "size", "points", "goodAnswers", "loggedUser", "firstName", "admin"})
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSession userSession;

    @RequestMapping("")
    public String home() {

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

        return "/course";
    }

    @RequestMapping("/aboutUs")
    public String aboutUs() {
        return "/aboutUs";
    }

    // registration user
    @GetMapping("/registration")
    public String registrationUser(Model model) {
        model.addAttribute("user", new User());
        return "/users/registration";
    }

    @PostMapping("/registration")
    public String registrationUser(@Validated(RegistrationValidator.class) User user, BindingResult result, Model model) {

        String message = userService.checkLoginAndPassword(user);
        if (!"registrationSuccess".equals(message)) {
            model.addAttribute("invalid", true);
            model.addAttribute("message", message);
            return "/users/registration";
        }
        if (result.hasErrors()) {
            return "/users/registration";
        }

        String passToHash = user.getPassword(); // hash password
        user.setPasswordHash(passToHash);

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
        if (!"loginSuccess".equals(check)) {
            request.setAttribute("login", login); //, by nie musieć wpisywać znowu
            request.setAttribute("pass", pass);
            return "/login";
        }
        model.addAttribute("loggedUser", true);
        userService.sessionStart(login); /////

        if (userSession.getUserInSession().isSuperUser() == true) {
            model.addAttribute("admin", true);

        }

        model.addAttribute("firstName", userSession.getUserInSession().getFirstName());
        model.addAttribute("registration", true);
        model.addAttribute("message", "Zalogowałeś się. Zapoznaj się z materiałami szkoleniowymi, wykonaj test.");
        return "/home";

    }

    @RequestMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("loggedUser", false);
        model.addAttribute("admin", false);
        model.addAttribute("firstName", "użytkowniku");

        userSession.setLoggedUser(false);
        userSession.setUserInSession(null);
        return "/home";
    }

}
