package ru.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import ru.course.dao.SurveyDAO;
import ru.course.model.Surveys.Survey;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
@Autowired
    SurveyDAO surveyDAO;

@GetMapping("/Surveys")
    public List<Survey> getAll(){
    return surveyDAO.getAll();
}
}
