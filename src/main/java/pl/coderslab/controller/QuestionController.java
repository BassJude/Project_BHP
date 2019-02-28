package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/questions")
@SessionAttributes({"questionNumber", "size", "points", "goodAnswers", "loggedUser", "firstName","admin"})
public class QuestionController {


    @Autowired
    private QuestionService questionService;

    @GetMapping("/test")
    public String beforeStartTest(Model model) {

        questionService.startSetting(model);

        return "forward:/questions/test/0";
    }

    @RequestMapping("/test/{number}")
    public String startTest(Model model, @PathVariable int number,
                            HttpSession session, HttpServletRequest request) {


        String answer = request.getParameter("answer");  // przy pierwszym wejsciu null
        String[] goodAnswer = (String[]) session.getAttribute("goodAnswers");
        int size = (int) session.getAttribute("size");

        if (answer != null) {
            if (goodAnswer[number - 1].equals(answer)) { // -1, bo pierwsze wejscie ma number=0 i nie wchodzi do ifa, bo answer jest nullem
                int points = (int) session.getAttribute("points");
                points++;
                model.addAttribute("points", points);

            }
        }

        if (number < size) {
            model.addAttribute("question", questionService.getNumberQuestionForTest(number)); // , wysylam pytanie, lista pytan jest od index 0
            model.addAttribute("questionNumber", number);

            return "questions/test";
        } else {
            return "forward:/questions/testResult";
        }
    }

    @RequestMapping("/testResult")
    public String finishTest(HttpSession session, Model model) {
        int points = (int) session.getAttribute("points");
        int size = (int) session.getAttribute("size");
        String evaluation = questionService.evaluation(points, size);
        model.addAttribute("evaluation", evaluation);

        return "/questions/testResult";
    }


}
