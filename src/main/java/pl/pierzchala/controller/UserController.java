package pl.pierzchala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.pierzchala.model.User;
import pl.pierzchala.model.UserSession;
import pl.pierzchala.service.UserService;
import pl.pierzchala.validator.EditValidator;

@Controller
@RequestMapping(path = "/users")
@SessionAttributes({"questionNumber", "size", "points", "goodAnswers", "loggedUser", "firstName", "admin"})
public class UserController {

    private UserService userService;
    private UserSession userSession;

    @Autowired
    public UserController(UserService userService, UserSession userSession) {
        this.userService = userService;
        this.userSession = userSession;
    }

    @GetMapping("/edit")
    public String editUser(Model model) {
        User user = userService.getUserSession();
        model.addAttribute("user", user);
        return "users/addEdit";
    }

    @PostMapping("/edit")
    public String saveEditUser(@Validated(EditValidator.class) User user, BindingResult result,
                               Model model) {
        return userService.saveEditUser(user, result, model);
    }

    @RequestMapping("/status")
    public String status(Model model) {
        return userService.status(model);
    }

    // powrót do ostatniego slajdu szkolenia
    @GetMapping("/continueTraining")
    public String getLastSlide(Model model) {
        if (userSession.isLoggedUser()) {
            model.addAttribute("numberImage", userService.findUserById(userSession.getUserInSession().getId()).getLastSlide());
        }
        return "/course";
    }
}
