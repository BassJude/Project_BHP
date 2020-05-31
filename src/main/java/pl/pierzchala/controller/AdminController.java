package pl.pierzchala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.pierzchala.model.Question;
import pl.pierzchala.model.User;
import pl.pierzchala.service.AdminService;
import pl.pierzchala.service.QuestionService;
import pl.pierzchala.service.UserService;
import pl.pierzchala.validator.EditValidator;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(path = "/admin")
@SessionAttributes({"questionNumber", "size", "points", "goodAnswers", "loggedUser", "firstName", "admin"})
public class AdminController {

    private UserService userService;
    private QuestionService questionService;
    private AdminService adminService;

    @Autowired
    public AdminController(UserService userService, QuestionService questionService, AdminService adminService) {
        this.userService = userService;
        this.questionService = questionService;
        this.adminService = adminService;
    }

    @RequestMapping("/")
    public String homeAdmin(Model model) {
        return adminService.homeAdmin(model);
    }

    @RequestMapping("/allUsers")
    public String allUsers(Model model) {
        return adminService.allUsers(model);
    }

    @GetMapping("/detailsUser/{id}")
    public String detailsUser(Model model, @PathVariable Long id) {
        return adminService.detailsUser(model, id);
    }

    @GetMapping("/editUser/{id}")
    public String getEditUser(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "admin/addEditUser";
    }

    @PostMapping("/editUser/{id}")
    public String saveEditUser(@Validated(EditValidator.class) User user, BindingResult result, Model model) {
        return adminService.saveEditUser(user, result, model);
    }

    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(Model model, @PathVariable Long id) {
        return adminService.deleteUser(model, id);
    }

    @RequestMapping("/searchUser")
    public String searchUser(@RequestParam(name = "search") String search,
                             @RequestParam(name = "examResult", defaultValue = "all") String examResult,
                             Model model) {

        return adminService.searchUser(search, examResult, model);
    }

    @ModelAttribute("abcd")
    public List<String> forSelect() {
        return Arrays.asList("A", "B", "C", "D");
    }

    @RequestMapping("/allQuestions")
    public String allQuestions(Model model) {
        List<Question> questions = questionService.findAll();
        model.addAttribute("questions", questions);
        return "admin/allQuestions";
    }

    @GetMapping("/addQuestion")
    public String editQuestion(Model model) {
        model.addAttribute("question", new Question());
        return "admin/addEditQuestion";
    }

    @PostMapping("/addQuestion")
    public String addQuestion(@Valid Question question, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/addEditQuestion";
        }
        questionService.save(question);
        return "forward:/admin/allQuestions";
    }

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
        return "forward:/admin/allQuestions";
    }

    @RequestMapping("/deleteQuestion/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestionById(id);
        return "forward:/admin/allQuestions";
    }

    @RequestMapping("/searchQuestion")
    public String searchQuestion(@RequestParam(name = "search") String search, Model model) {
        return adminService.searchQuestion(search, model);
    }

    // potem do wywalenia TODO
    @GetMapping("/hash")
    @ResponseBody
    public String hashPasswordIfNotHash() {
        List<User> userList = userService.findAll();
        for (User u : userList) {
            if (u.getPassword().length() != 60) {
                String password = u.getPassword();
                u.setPasswordHash(password);
                userService.save(u);
            }
        }
        return "All passwords hashed";
    }
}
