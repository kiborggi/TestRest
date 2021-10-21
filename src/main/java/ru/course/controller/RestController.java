package ru.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.course.dao.SurveyDAO;
import ru.course.model.Surveys.Category;
import ru.course.model.Surveys.Option;
import ru.course.model.Surveys.Question;
import ru.course.model.Surveys.Survey;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("api")
public class RestController {
@Autowired
    SurveyDAO surveyDAO;

@GetMapping("/Surveys")
    public List<Survey> getAll(){
    return surveyDAO.getAll();


}


@GetMapping("/user")
    public Principal userInfo(Principal principal){
    return principal;
}


}
