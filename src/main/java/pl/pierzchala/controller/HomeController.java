package pl.pierzchala.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.pierzchala.model.User;
import pl.pierzchala.service.HomeService;
import pl.pierzchala.validator.RegistrationValidator;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes({"questionNumber", "size", "points", "goodAnswers", "loggedUser", "firstName", "admin"})
public class HomeController {

    private HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/course/{number}")
    public String course(@PathVariable int number, Model model) {
        homeService.setSlide(number, model);
        return "/course";
    }

    @PostMapping("/course/number")
    public String getSlide(@RequestParam int number, Model model) {
        homeService.setSlide(number, model);
        return "/course";
    }

    @RequestMapping("/aboutUs")
    public String aboutUs() {
        return "/aboutUs";
    }

    @GetMapping("/registration")
    public String registrationUser(Model model) {
        model.addAttribute("user", new User());
        return "/users/registration";
    }

    @PostMapping("/registration")
    public String registrationUser(@Validated(RegistrationValidator.class) User user, BindingResult result, Model model) {
        return homeService.registrationUser(user, result, model);
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @PostMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        return homeService.loginPost(model, request);

    }

    @RequestMapping("/logout")
    public String logout(Model model) {
        return homeService.logout(model);
    }
}
