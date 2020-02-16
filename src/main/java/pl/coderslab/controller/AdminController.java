package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.Question;
import pl.coderslab.model.User;
import pl.coderslab.model.UserSession;
import pl.coderslab.service.QuestionService;
import pl.coderslab.service.UserService;
import pl.coderslab.validator.EditValidator;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/admin")
@SessionAttributes({"questionNumber", "size", "points", "goodAnswers", "loggedUser", "firstName", "admin"})
public class AdminController {


    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserSession userSession;

    /////////////////// users///////////////////

    @RequestMapping("")
    public String home() {

        return "/admin";
    }

    @RequestMapping("/allUsers")
    public String all(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        return "admin/allUsers";
    }

    //edit user
    @GetMapping("/editUser/{id}")
    public String editUser(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "admin/addEditUser";
    }

    @PostMapping("/editUser/{id}")
    public String saveUser(@Validated(EditValidator.class) User user, BindingResult result, @PathVariable Long id, Model model) {
        if (result.hasErrors()) {
            return "admin/addEditUser";
        }

        // user, ktorego chcemy zaktualizować nie ma loginu, hasla i daty zaliczenia testu
        // dlatego musimy uzupełnić te dane
        User userFromDB = userService.findUserById(id);
        user.setLogin(userFromDB.getLogin());
        user.setPassword(userFromDB.getPassword());
        user.setLastTestTime(userFromDB.getLastTestTime());
//        model.addAttribute("admin", user.isSuperUser()); // zeby po zmianie od razu wylogowało ze astrony administracyjnej, ale to wywala ze strony admina !!!

        userService.save(user);
        return "forward:/admin/allUsers";
    }

    // delete user
    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "forward:/admin/allUsers";
    }

    // search user
    @RequestMapping("/search")
    public String search(@RequestParam(name = "search") String search, Model model) {

        List<User> users = userService.searchUser(search);

        model.addAttribute("users", users);
        return "admin/allUsers";

    }

    // not passed
    @RequestMapping("/notPassedEgzam")
    public String notPassed(Model model, @RequestParam(defaultValue = "false", name = "passed") boolean passed) {


        model.addAttribute("users", userService.passedEgzam(passed));


        return "admin/allUsers";
    }

////////////// questions /////////////////////

    @ModelAttribute("abcd")
    public List<String> forSelect() {
        List<String> stringList = new ArrayList<>();
        stringList.add("A");
        stringList.add("B");
        stringList.add("C");
        stringList.add("D");
        return stringList;
    }


    @RequestMapping("/allQuestions")
    public String allQuestions(Model model) {
        List<Question> questions = questionService.findAll();
        model.addAttribute("questions", questions);
        return "admin/allQuestions";
    }

    // add question
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
        return "forward:/admin/allQuestions";
    }

    // delete question
    @RequestMapping("/deleteQuestion/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestionById(id);
        return "forward:/admin/allQuestions";
    }

    // search question
    @RequestMapping("/searchQuestion")
    public String searchQuestion(Model model, @RequestParam(name = "search") String search) {
        List<Question> questions = questionService.searchQuestion(search);
        model.addAttribute("questions", questions);
        return "admin/allQuestions";
    }


}
