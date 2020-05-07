package pl.pierzchala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.pierzchala.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/questions")
@SessionAttributes({"questionNumber", "size", "points", "goodAnswers", "loggedUser", "firstName", "admin"})
public class QuestionController {


    @Autowired
    private QuestionService questionService;

    @GetMapping("/test")
    public String beforeStartTest(Model model) {
        questionService.settingsBeforeStartTest(model);
        return "forward:/questions/test/0";
    }

    @RequestMapping("/test/{number}")
    public String test(Model model, @PathVariable int number,
                       HttpSession session, HttpServletRequest request) {
        return questionService.test(model, number, session, request);
    }

    @RequestMapping("/testResult")
    public String finishTest(HttpSession session, Model model) {
        questionService.finishTest(session, model);
        return "/questions/testResult";
    }


}
