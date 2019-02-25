package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.Question;
import pl.coderslab.model.User;
import pl.coderslab.service.QuestionService;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.EditValidator;
import pl.coderslab.validator.RegistrationValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/admin")
public class AdminController {


    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    /////////////////// users///////////////////

    @ModelAttribute("users")
    public List<User> getUsers() {
        return userService.findAll();
    }


    @RequestMapping("/allUsers")
    public String all() {
        return "admin/allUsers";
    }

    // add user
    @GetMapping("/addUser")
    public String editUser(Model model) {
        model.addAttribute("user", new User());
        return "/admin/addEditUser";
    }

    @PostMapping("/addUser")
    public String addUser(@Validated(RegistrationValidator.class) User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/addEditUser";
        }
        userService.save(user);
        return "redirect:/admin/allUsers";
    }

    //edit user
    @GetMapping("/editUser/{id}")
    public String editUser(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "admin/addEditUser";
    }

    @PostMapping("/editUser/{id}")
    public String saveUser(@Validated(EditValidator.class) User user, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "admin/addEditUser";
        }
        // TODO mozna zmienić, potem....
        User userToSave = userService.findUserById(id);
        user.setLogin(userToSave.getLogin());
        user.setPassword(userToSave.getPassword());

        userService.save(user);
        return "redirect:/admin/allUser";
    }

    // delete user
    @RequestMapping("deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/";
    }

////////////// questions /////////////////////

    @ModelAttribute("questions")
    public List<Question> getQuestions(Model model) {
        return questionService.findAll();
    }

    @RequestMapping("/allQuestions")
    public String allQuestions() {
        return "admin/allQuestions";
    }

    // add question
    @GetMapping("addQuestion")
    public String editQuestion(Model model) {
        model.addAttribute("question", new Question());
        return "admin/addEditQuestion";
    }

    @PostMapping("addQuestion")
    public String addQuestion(@Valid Question question, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/addEditQuestion";
        }
        questionService.save(question);
        return "redirect:/admin/allQuestions";
    }


    //edit question
    @GetMapping("/editQuestion/{id}")
    public String editQuestion(Model model, @PathVariable Long id) {
        model.addAttribute("question", questionService.findQuestionById(id));
        return "admin/addEditQuestion";
    }

    @PostMapping("/editQuestion/{id}")
    public String saveQuestion(@Valid Question question, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/addEditQuestion";
        }
        questionService.update(question);
        return "redirect:/admin/allQuestions";
    }

    // delete question
    @RequestMapping("deleteQuestion/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestionById(id);
        return "redirect:/admin/allQuestions";
    }


}
