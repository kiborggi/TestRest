package ru.course.dao;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.course.dao.Interfaces.SurveyRepository;
import ru.course.model.Surveys.Survey;

import java.util.List;


@Component
public class SurveyDAO {
@Autowired
private SurveyRepository surveyRepository;

public Survey addSurvey(Survey survey){
    return surveyRepository.saveAndFlush(survey);
}
    public List<Survey> getAll(){
    return (List<Survey>) surveyRepository.findAll();
}

 public List<Survey> getByUserId(long u){
    return surveyRepository.findAllByUserId(u);
 }


    public SurveyDAO() {
    }



}
