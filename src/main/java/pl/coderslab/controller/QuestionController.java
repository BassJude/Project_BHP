package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.coderslab.model.Question;
import pl.coderslab.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/questions")
@SessionAttributes({"questionNumber", "size", "points", "goodAnswers", "loggedUser", "firstName"})
public class QuestionController {


    @Autowired
    private QuestionService questionService;


    @ModelAttribute("/questions")
    public List<Question> getQuestions(Model model) {
        return questionService.findAll();
    }

    @ModelAttribute("abcd")
    public List<String> forSelect() {
        List<String> stringList = new ArrayList<>();
        stringList.add("A");
        stringList.add("B");
        stringList.add("C");
        stringList.add("D");
        return stringList;
    }


    //test

    // points = 0
    @RequestMapping("/test")
    public String zeroPoints(HttpSession session, Model model) {
        // int points = (int)session.getAttribute("points");
        int points = 0;
        model.addAttribute("points", points);
        return "redirect:/questions/test/0";
    }

    @RequestMapping("/test/{number}")
    public String startTest(Model model, @PathVariable int number,
                            HttpSession session, HttpServletRequest request) {


        String answer = request.getParameter("answer");
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
            model.addAttribute("question", questionService.getNumberQuestionForTest(number)); // lista pytan jest od index 0
            model.addAttribute("questionNumber", number);


            return "questions/test";
        } else {
            return "redirect:/questions/testResult";
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
