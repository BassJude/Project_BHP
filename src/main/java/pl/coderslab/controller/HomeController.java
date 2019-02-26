package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.service.QuestionService;

@Controller
@SessionAttributes({"questionNumber", "size", "points", "goodAnswers"})
public class HomeController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("")
    public String home(Model model) {

        questionService.startSetting(model);


        return "home";
    }

    @RequestMapping("/course/{number}")
    public String course(@PathVariable int number, Model model) {
        if (number < 1) {
            number = 1;
        }
        if (number > 37) {
            number = 37;
        }
        model.addAttribute("numberImage", number);

        return "/course";
    }

}
