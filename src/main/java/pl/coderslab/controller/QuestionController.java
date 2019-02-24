package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.model.Question;
import pl.coderslab.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/questions")
@SessionAttributes({"questionNumber", "size", "points", "goodAnswers"})
public class QuestionController {


    @Autowired
    private QuestionService questionService;


    @ModelAttribute("questions")
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

    @RequestMapping("/all")
    public String all() {
        return "questions/all";
    }

    // add question
    @GetMapping("add")
    public String editQuestion(Model model) {
        model.addAttribute("question", new Question());
        return "questions/addEdit";
    }

    @PostMapping("add")
    public String addQuestion(@Valid Question question, BindingResult result) {
        if (result.hasErrors()) {
            return "questions/addEdit";
        }
        questionService.save(question);
        return "redirect:/questions/all";
    }

    //edit question
    @GetMapping("/edit/{id}")
    public String editQuestion(Model model, @PathVariable Long id) {
        model.addAttribute("question", questionService.findQuestionById(id));
        return "questions/addEdit";
    }

    @PostMapping("/edit/{id}")
    public String saveQuestion(@Valid Question question, BindingResult result) {
        if (result.hasErrors()) {
            return "questions/addEdit";
        }
        questionService.update(question);
        return "redirect:/questions/all";
    }

    // delete question
    @RequestMapping("delete/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestionById(id);
        return "redirect:/questions/all";
    }

    //test
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
            model.addAttribute("question", questionService.getNumberQuestionForTest(number));
            model.addAttribute("questionNumber", number);


            return "questions/test";
        } else {
            return "questions/testResult";
        }
    }


}
