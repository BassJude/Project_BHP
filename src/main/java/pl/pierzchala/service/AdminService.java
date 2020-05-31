package pl.pierzchala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.pierzchala.model.Question;
import pl.pierzchala.model.User;
import pl.pierzchala.model.UserSession;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionAttributes({"questionNumber", "size", "points", "goodAnswers", "loggedUser", "firstName", "admin"})
public class AdminService {

    private UserService userService;
    private QuestionService questionService;
    private UserSession userSession;

    @Autowired
    public AdminService(UserService userService, QuestionService questionService, UserSession userSession) {
        this.userService = userService;
        this.questionService = questionService;
        this.userSession = userSession;
    }

    public String homeAdmin(Model model) {
        model.addAttribute("numberOfUsers", userService.findAll().size());
        model.addAttribute("numberOfQuestions", questionService.findAll().size());
        model.addAttribute("percentageOfPassedExams", userService.percentageOfPassedExams());
        return "/admin";
    }

    public String allUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/allUsers";
    }

    public String detailsUser(Model model, Long id) {
        User userById = userService.findUserById(id);
        List<User> userList = new ArrayList<>();
        userList.add(userById);
        model.addAttribute("users", userList);
        return "admin/detailsUser";
    }

    public String saveEditUser(User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "admin/addEditUser";
        }
        // Zawsze jeden użytkownik musi być adminem
        if (userService.quantitySuperUsers() == 1 && (userService.findUserById(user.getId()).isSuperUser())) {
            model.addAttribute("AdminInvalid", true);
            user.setSuperUser(true);
        }

        // uzupełniamy dane, które nie przyszły z formularza
        User userFromDB = userService.findUserById(user.getId());
        user.setLogin(userFromDB.getLogin());
        user.setPassword(userFromDB.getPassword());
        user.setLastTestTime(userFromDB.getLastTestTime());

        // zaktualizowanie usera w sesji
        if (user.getId().equals(userSession.getUserInSession().getId())) {
            userSession.setUserInSession(user);
            model.addAttribute("firstName", user.getFirstName());
        }
        userService.save(user);
        return "forward:/admin/allUsers";
    }

    public String deleteUser(Model model, Long id) {
        // sprawdzamy, czy taki user istnieje, ponieważ mógł wcześniej zostać usunięty
        if (userService.findUserById(id) == null) {
            model.addAttribute("lackOfUserInDB", true);
            model.addAttribute("ID", id);
            return "forward:/admin/allUsers";
        }

        // nie kasujemy ostatniego admina
        if (userService.quantitySuperUsers() == 1 && (userService.findUserById(id).isSuperUser())) {
            model.addAttribute("AdminInvalid", true);
            return "forward:/admin/allUsers";
        }

        User user = userService.findUserById(id);

        // nie kasujemy samego siebie
        if (user.getId().equals(userSession.getUserInSession().getId())) {
            model.addAttribute("AdminInvalid", true);
            return "forward:/admin/allUsers";
        }
        model.addAttribute("userDelete", true);
        model.addAttribute("user", user);
        userService.deleteById(id);
        return "forward:/admin/allUsers";
    }

    public String searchUser(String search, String examResult, Model model) {
        List<User> users = null;
        // trim() bo do inputa dodawana jest spacja po każdym wyszukaniu
        search = search.trim();
        if (search.length() == 0) {
            users = userService.findAll();
        } else {
            users = userService.searchUser(search);
        }

        //filtrujemy użytkowników
        List<User> result = userFilter(examResult, users);

        model.addAttribute("users", result);
        model.addAttribute("search", search.trim());
        model.addAttribute("examResult", examResult);
        return "admin/allUsers";
    }

    private List<User> userFilter(String examResult, List<User> users) {
        List<User> result = new ArrayList<>();
        switch (examResult) {
            case "passed":
                for (User u : users) {
                    if (u.isExamPassed()) {
                        result.add(u);
                    }
                }
                break;
            case "fail":
                for (User u : users) {
                    if (!u.isExamPassed()) {
                        result.add(u);
                    }
                }
                break;
            case "all":
                result.addAll(users);
                break;
        }
        return result;
    }

    public String searchQuestion(String search, Model model) {
        List<Question> questions = questionService.searchQuestion(search.trim());
        model.addAttribute("questions", questions);
        model.addAttribute("search", search.trim());
        model.addAttribute("searching", true);
        return "admin/allQuestions";
    }
}
