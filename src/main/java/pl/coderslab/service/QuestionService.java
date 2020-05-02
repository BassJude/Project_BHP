package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.coderslab.model.Question;
import pl.coderslab.model.User;
import pl.coderslab.model.UserSession;
import pl.coderslab.repository.QuestionRepository;
import pl.coderslab.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserSession userSession;

    public void save(Question question) {
        questionRepository.save(question);
    }

    public void update(Question question) {
        questionRepository.save(question);
    }

    public void deleteQuestionById(Long id) {
        questionRepository.delete(id);
    }

    public void deleteQuestion(Question question) {
        questionRepository.delete(question);
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

    // starting settings
    public void startSetting(Model model) {
        // how many questions we have
        List<Question> questionList = questionRepository.findAll();
        int size = questionList.size();
        model.addAttribute("size", size);

        // table of answers
        String[] goodAnswer = new String[size];
        for (int i = 0; i < size; i++) {
            goodAnswer[i] = questionList.get(i).getGood_answer();
        }
        model.addAttribute("goodAnswers", goodAnswer);
        int points = 0;
        model.addAttribute("points", points);
    }

    public String evaluation(int points, int numberOfQuestions) {

        if (points < (numberOfQuestions / 2)) {
            return "niezaliczony";
        } else {
            User userInSession = userSession.getUserInSession();
            userInSession.setLastTestTime(LocalDateTime.now());
            userInSession.setPassedEgzam(true);
            userRepository.save(userInSession);
            return "zaliczony";
        }
    }

    // search
    public List<Question> searchQuestion(String search) {
        return questionRepository.findQuestionByQuestionContaining(search);
    }


}
