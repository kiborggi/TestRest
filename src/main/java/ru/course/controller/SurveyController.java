package ru.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.view.RedirectView;
import ru.course.dao.AppUserDAO;
import ru.course.model.Surveys.Category;
import ru.course.model.Surveys.Option;
import ru.course.model.Surveys.Question;
import ru.course.model.Surveys.Survey;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;

@Controller
@RequestMapping("survey")

public class SurveyController {

    @Autowired
    private AppUserDAO appUserDAO;

    @RequestMapping (value ="/createSurvey", method = RequestMethod.GET)
    public String createSurvey1 (Model model){


        return "Survey/createSurvey";
    }

    @RequestMapping (value ="/initSurveyCreate", method = RequestMethod.POST)
    public RedirectView initSurveyCreate (Model model, Survey survey, Principal principal, HttpServletRequest request){

        survey.setUserId(appUserDAO.findUserAccount(principal.getName()).getUserId());
        survey.setCategory(new ArrayList<Category>());
        survey.setQuestion(new ArrayList<Question>());
        request.getSession().setAttribute("Survey", survey);
        return new RedirectView("createCategory");
    }

    @RequestMapping (value = "/createCategory" ,method = RequestMethod.GET)
    public String createCategory(HttpServletRequest request, Model model){
        Survey survey = (Survey) request.getSession().getAttribute("Survey");
        model.addAttribute("survey",survey);
        return "Survey/createCategory";
    }

    @RequestMapping (value ="/initCategory", method = RequestMethod.POST)
    public RedirectView initOption (Model model, Category category, HttpServletRequest request){
        Survey survey = (Survey) request.getSession().getAttribute("Survey");
        survey.getCategory().add(category);

        return new RedirectView("createCategory");
    }

    @RequestMapping (value = "/createQuestion" ,method = RequestMethod.GET)
    public String createQuestion(HttpServletRequest request, Model model){
        Survey survey = (Survey) request.getSession().getAttribute("Survey");
        model.addAttribute("survey",survey);
        return "Survey/createQuestion";
    }

    @RequestMapping (value ="/initQuestion", method = RequestMethod.POST)
    public RedirectView initQuestion (Model model, Question question, HttpServletRequest request){
        Survey survey = (Survey) request.getSession().getAttribute("Survey");
        ArrayList<Option> optionList = new ArrayList<Option>();
        for (int i = 0; i< question.getNumberOfOptions(); i++) {
            optionList.add(new Option());
        }
        question.setOption(optionList);
        survey.getQuestion().add(question);
        model.addAttribute("survey",survey);
        return new RedirectView("createQuestion");
    }


    @RequestMapping (value = "/createOption" ,method = RequestMethod.GET)
    public String createOption(HttpServletRequest request, Model model){
        Survey survey = (Survey) request.getSession().getAttribute("Survey");
        model.addAttribute("survey",survey);
        return "Survey/createOption";
    }



    @ModelAttribute (value = "Survey")
    public Survey newSurvey(){
        return  new Survey();
    }

    @ModelAttribute (value = "Question")
    public Question newQuestion(){
        return  new Question();
    }

    @ModelAttribute (value = "Category")
    public Category newCategory(){
        return  new Category();
    }


}
