package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.model.User;
import pl.coderslab.model.UserSession;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.EditValidator;

import java.time.LocalDateTime;

@Controller
@RequestMapping(path = "/users")
@SessionAttributes({"questionNumber", "size", "points", "goodAnswers", "loggedUser", "firstName", "admin"})
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
        // TODO potestuj  co sie stanie jak nie zaktualizuje niektorych ponizszych danych
        User userToSave = userService.findUserById(user.getId());
        user.setLogin(userToSave.getLogin());
        user.setPassword(userToSave.getPassword());
        user.setLastTestTime(userToSave.getLastTestTime());
        user.setPassedEgzam(userToSave.isPassedEgzam());
        user.setSuperUser(userToSave.isSuperUser());
//
        userService.save(user);
        userSession.setUserInSession(user);// zaktualizowanie
        model.addAttribute("changes", true);
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("message", "Profil zaktualizowany");
        return "/home";
    }

    // status
    @RequestMapping("/status")
    public String status(Model model) {
        User user = userSession.getUserInSession();
        model.addAttribute("status", user.isPassedEgzam());

        LocalDateTime date = user.getLastTestTime();
        if (date != null) {

            String lastTime = date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth() + "|" + date.getHour() + ":" + date.getMinute();
            model.addAttribute("time", lastTime);
        }

        return "/users/status";

    }


}
