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
import pl.coderslab.validator.EditValidator;
import pl.coderslab.validator.RegistrationValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
@RequestMapping(path = "/users")
@SessionAttributes({"questionNumber", "size", "points", "goodAnswers", "loggedUser"})
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserSession userSession;


    //edit user
    @GetMapping("/edit")
    public String editUser(Model model) {
        User user = userService.getUserSession();
        model.addAttribute("user", user);

        return "users/addEdit";
    }

    @PostMapping("/edit")
    public String saveUser(@Validated(EditValidator.class) User user, BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            return "users/addEdit";
        }
        User userToSave = userService.findUserById(user.getId());
        user.setLogin(userToSave.getLogin());
        user.setPassword(userToSave.getPassword());
        user.setLastTestTime(userToSave.getLastTestTime());
        user.setPassedEgzam(userToSave.isPassedEgzam());
        user.setSuperUser(userToSave.isSuperUser());

        userService.save(user);
        model.addAttribute("changes", true);
        model.addAttribute("message", "Profil zaktualizowany");
        return "/home";
    }

    // registration user
    @GetMapping("/registration")
    public String registrationUser(Model model) {
        model.addAttribute("user", new User());
        return "users/registration";
    }

    @PostMapping("/registration")
    public String registrationUser(@Validated(RegistrationValidator.class) User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "users/registration";
        }
        String check = userService.checkRegistration(user);
        if (!"registrationSucces".equals(check)) {
            model.addAttribute("invalid", true);
            model.addAttribute("message", check);
            return "users/registration";
        }

        userService.save(user);
        model.addAttribute("registration", true);
        model.addAttribute("message", "Dziękujemy za rejestrację. Teraz możesz się zalogować.");
        return "/home";
    }

    // login
    @GetMapping("/login")
    public String login() {
        return "/users/login";
    }

    @PostMapping("/login")
    public String login(Model model, HttpServletRequest request, HttpSession session) {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        String check = userService.checkLogin(login, pass, model);
        if (!"loginSucces".equals(check)) {
            request.setAttribute("login", login);
            request.setAttribute("pass", pass);
            return "users/login";
        }
        model.addAttribute("loggedUser", true);
        userService.sessionStart(login);
        model.addAttribute("registration", true);
        model.addAttribute("message", "Zalogowałeś się. Zapoznaj się z materiałami szkoleniowymi, wykonaj test.");
        return "/home";

    }
    @RequestMapping("/logout")
    public String logout(Model model){
        model.addAttribute("loggedUser",false);
        userSession.setLoggedUser(false);
        userSession.setUserInSession(null);
        return "/home";
    }

    @RequestMapping("/status")
    public String status(Model model){
        User user = userSession.getUserInSession();
        model.addAttribute("status", user.isPassedEgzam());

        LocalDateTime date = user.getLastTestTime();
        if (date!=null) {

            String lastTime = date.getYear()+"-"+date.getMonthValue()+"-"+date.getDayOfMonth()+"|"+date.getHour()+":"+date.getMinute();
            model.addAttribute("time", lastTime);
        }

        return "/users/status";


    }


}
