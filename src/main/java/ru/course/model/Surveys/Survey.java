package ru.course.model.Surveys;

import javax.persistence.*;

@Entity
@Table(name = "survey")
public class Survey {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)

    int SurveyId;




    String name;



    public Survey() {
    }

    public int getSurveyId() {
        return SurveyId;
    }

    public void setSurveyId(int surveyId) {
        SurveyId = surveyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
