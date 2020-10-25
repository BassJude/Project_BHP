package pl.pierzchala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.pierzchala.model.Question;
import pl.pierzchala.model.User;
import pl.pierzchala.model.UserSession;
import pl.pierzchala.repository.QuestionRepository;
import pl.pierzchala.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Service
@SessionAttributes({"questionNumber", "size", "points", "goodAnswers", "loggedUser", "firstName", "admin"})
public class QuestionService {

    private QuestionRepository questionRepository;
    private UserRepository userRepository;
    private UserSession userSession;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository, UserSession userSession) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.userSession = userSession;
    }

    public void save(Question question) {
        questionRepository.save(question);
    }

    public void update(Question question) {
        questionRepository.save(question);
    }

    public void deleteQuestionById(Long id) {
        questionRepository.delete(id);
    }

    public Question findQuestionById(Long id) {
        return questionRepository.findOne(id);
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Question getNumberQuestionForTest(int number) {
        List<Question> questionList = questionRepository.findAll();
        return questionList.get(number);
    }

    // ustawienia początkowe testu
    public void settingsBeforeStartTest(Model model) {
        // zliczenie liczby pytań
        List<Question> questionList = questionRepository.findAll();
        int size = questionList.size();
        model.addAttribute("size", size);

        // zbudowanie tabeli z poprawnymi odpowiedziami
        String[] goodAnswer = new String[size];
        for (int i = 0; i < size; i++) {
            goodAnswer[i] = questionList.get(i).getGood_answer();
        }
        model.addAttribute("goodAnswers", goodAnswer);
        int points = 0;
        model.addAttribute("points", points);
    }

    public String test(Model model, int number, HttpSession session, HttpServletRequest request) {

        String answer = request.getParameter("answer");  // przy pierwszym wejsciu answer == null
        // pobieramy tablię dobrych odpowiedzi
        String[] goodAnswer = (String[]) session.getAttribute("goodAnswers");
        // liczba wszystkich pytań
        int size = (int) session.getAttribute("size");

        // zliczanie punktów
        if (answer != null) {
            if (goodAnswer[number - 1].equals(answer)) { // number -1, bo pierwsze wejscie ma number=0 i nie wchodzi do ifa, bo answer jest nullem
                int points = (int) session.getAttribute("points");
                points++;
                model.addAttribute("points", points);
            }
        }

        // jeżeli numer pytania jest mniejszy od liczby wszystkich pytań, kontynuujemy test
        if (number < size) {
            model.addAttribute("question", getNumberQuestionForTest(number)); // , wysylam pytanie, lista pytan jest od index 0
            model.addAttribute("questionNumber", number);
            return "questions/test";
        } else {
            // odpowiedzieliśmy na wszystkie pytania
            return "forward:/questions/testResult";
        }
    }

    public void finishTest(HttpSession session, Model model) {
        int points = (int) session.getAttribute("points");
        int size = (int) session.getAttribute("size");
        String evaluation = evaluation(points, size);
        model.addAttribute("evaluation", evaluation);
    }

    private String evaluation(int points, int numberOfQuestions) {

        if (points < (numberOfQuestions / 2)) {
            return "niezaliczony";
        } else {
            User userInSession = userSession.getUserInSession();
            userInSession.setLastTestTime(LocalDateTime.now());
            userInSession.setExamPassed(true);
            userRepository.save(userInSession);
            return "zaliczony";
        }
    }

    // wyszukiwanie pytania
    public List<Question> searchQuestion(String search) {
        return questionRepository.findQuestionByQuestionContaining(search);
    }
}
