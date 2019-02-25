package pl.coderslab.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    @NotBlank
    @Size(max=255, message = "Treść pytania nie moze mieć więcej niż 255 znaków")
    private String question;

    @Column(length = 100)
    @NotBlank
    @Size(max=100, message = "Treść odpowiedzi nie moze mieć więcej niż 100 znaków")
    private String answer1;
    @Column(length = 100)
    @NotBlank
    @Size(max=100, message = "Treść odpowiedzi nie moze mieć więcej niż 100 znaków")
    private String answer2;
    @Column(length = 100)
    @NotBlank
    @Size(max=100, message = "Treść odpowiedzi nie moze mieć więcej niż 100 znaków")
    private String answer3;
    @Column(length = 100)
    @NotBlank
    @Size(max=100, message = "Treść odpowiedzi nie moze mieć więcej niż 100 znaków")
    private String answer4;

    @Column(length = 5)
    @NotEmpty(message = "Wybierz odpowiedź")
    @NotNull(message = "Wybierz odpowiedź")
    @Size(max=100, message = "Wybierz jedną odpowiedź")
    private String good_answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getGood_answer() {
        return good_answer;
    }

    public void setGood_answer(String good_answer) {
        this.good_answer = good_answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer1='" + answer1 + '\'' +
                ", answer2='" + answer2 + '\'' +
                ", answer3='" + answer3 + '\'' +
                ", answer4='" + answer4 + '\'' +
                ", good_answer='" + good_answer + '\'' +
                '}';
    }
}
