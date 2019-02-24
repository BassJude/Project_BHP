package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.User;
import pl.coderslab.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/users")
public class UserController {



    @Autowired
    private UserService userService;

    @ModelAttribute("users")
    public List<User> getUsers() {
        return userService.findAll();
    }



    @RequestMapping("/all")
    public String all() {
        return "users/all";
    }

    // add user
    @GetMapping("add")
    public String editUser(Model model) {
        model.addAttribute("user", new User());
        return "users/addEdit";
    }

    @PostMapping("add")
    public String addUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "users/addEdit";
        }
        userService.save(user);
        return "redirect:/users/all";
    }

    //edit question
    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "users/addEdit";
    }

    @PostMapping("/edit/{id}")
    public String saveUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "users/addEdit";
        }
        userService.save(user);
        return "redirect:/users/all";
    }

    // delete question
    @RequestMapping("delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/users/all";
    }




}
