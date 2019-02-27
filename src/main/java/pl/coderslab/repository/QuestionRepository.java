package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.model.Question;

import java.awt.print.Book;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    // TODO dokońćzyć wyszukiwanie
    @Query("select question from Question question where question.question like %?1 or question.answer1 like %?1")
    List<Question> searchAllLike(String title);

    List<Question> findAllByQuestionContaining(String search);

}
