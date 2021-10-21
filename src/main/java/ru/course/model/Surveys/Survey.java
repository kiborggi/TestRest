package ru.course.model.Surveys;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "survey")
public class Survey {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    long SurveyId;

    String name;



    long userId;

    @OneToMany (fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    List<Question> question;
    @OneToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    List<Category> category;




    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public Survey() {
    }

    public long getSurveyId() {
        return SurveyId;
    }

    public void setSurveyId(long surveyId) {
        SurveyId = surveyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
