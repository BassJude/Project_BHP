package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.User;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.EditValidator;
import pl.coderslab.validator.RegistrationValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/users")
public class UserController {


    @Autowired
    private UserService userService;



    //edit user
    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "users/addEdit";
    }

    @PostMapping("/edit/{id}")
    public String saveUser(@Validated(EditValidator.class) User user, BindingResult result, @PathVariable Long id,
                           Model model) {
        if (result.hasErrors()) {
            return "users/addEdit";
        }
        User userToSave = userService.findUserById(id);
        user.setLogin(userToSave.getLogin());
        user.setPassword(userToSave.getPassword());

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
        if (!"OK".equals(check)) {
            model.addAttribute("invalid", true);
            model.addAttribute("message", check);
            return "users/registration";
        }


        userService.save(user);
        model.addAttribute("registration", true);
        model.addAttribute("message", "Dziękujemy za rejestrację. Teraz możesz się zalogować.");
        return "/home";
    }


}
