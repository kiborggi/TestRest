package ru.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.course.dao.AppUserDAO;
import ru.course.dao.Interfaces.CategoryRepo;
import ru.course.dao.Interfaces.OptionRepository;
import ru.course.dao.Interfaces.QuestionRepository;
import ru.course.dao.Interfaces.SurveyRepository;
import ru.course.dao.SurveyDAO;
import ru.course.model.Surveys.Category;
import ru.course.model.Surveys.Option;
import ru.course.model.Surveys.Question;
import ru.course.model.Surveys.Survey;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("survey")

public class SurveyController {

    @Autowired
    private AppUserDAO appUserDAO;
    @Autowired
    private SurveyDAO surveyDAO;
    @Autowired
    SurveyRepository surveyRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    OptionRepository optionRepository;
    @Autowired
    CategoryRepo categoryRepo;


    @RequestMapping (value ="/mySurvreys", method = RequestMethod.GET)
    public String mySurvreys (Model model,Principal principal){
        model.addAttribute("surveyList", surveyDAO.getByUserId(appUserDAO.findUserAccount(principal.getName()).getUserId()));
        return "Survey/mySurvreys";
    }
    @RequestMapping (value ="/createSurvey", method = RequestMethod.GET)
    public String createSurvey1 (Model model){

        return "Survey/createSurvey";
    }


    @RequestMapping (value ="/deleteSurvey/{id}", method = RequestMethod.GET)
    public RedirectView deleteSurvey (Model model,Survey survey,@PathVariable(value="id") String idS){
        long id = Integer.parseInt(idS);
        System.out.println(id);
        surveyRepository.deleteById(id);
        return new RedirectView("/survey/mySurvreys");
    }

    @RequestMapping (value ="/editSurvey/{id}", method = RequestMethod.GET)
    public String editSurvey (Model model,@PathVariable(value="id") String idS){
        int id = Integer.parseInt(idS);
        model.addAttribute("survey", surveyRepository.findBySurveyId(id));
        return "Survey/editSurvey";
    }

    @RequestMapping (value ="/initSurveyCreate", method = RequestMethod.POST)
    public RedirectView initSurveyCreate (Model model, Survey survey,Principal principal, HttpServletRequest request){

        survey.setUserId(appUserDAO.findUserAccount(principal.getName()).getUserId());
        survey.setCategory(new ArrayList<Category>());
        survey.setQuestion(new ArrayList<Question>());
        surveyDAO.addSurvey(survey);
        return new RedirectView("/survey/mySurvreys");
    }



    @RequestMapping (value ="/initCategory/{surveyId}", method = RequestMethod.POST)
    public RedirectView initOption (Model model, Category category, HttpServletRequest request,@PathVariable(value="surveyId") String idS){
        int id = Integer.parseInt(idS);
        Survey survey = surveyRepository.findBySurveyId(id);

        survey.getCategory().add(category);
       surveyRepository.saveAndFlush(survey);
        return new RedirectView("/survey/editSurvey/" + id);
    }

    @RequestMapping (value ="/initQuestion/{surveyId}", method = RequestMethod.POST)
    public RedirectView initQuestion (Model model, Question question, HttpServletRequest request,@PathVariable(value="surveyId") String idS){
        int id = Integer.parseInt(idS);
        Survey survey = surveyRepository.findBySurveyId(id);
        ArrayList<Option> optionList = new ArrayList<Option>();
        for (int i = 0; i< question.getNumberOfOptions(); i++) {
            optionList.add(new Option());
        }
        question.setOption(optionList);
        survey.getQuestion().add(question);
        model.addAttribute("survey",survey);
        surveyDAO.addSurvey(survey);
        return new RedirectView("/survey/editSurvey/" + id);
    }




    @RequestMapping (value ="deleteCategory/{id}", method = RequestMethod.GET)
    public RedirectView deleteCategory (Model model, Category category, HttpServletRequest request){

        Survey survey = surveyRepository.findSurveyByCategory(category);

        System.out.println(survey.getCategory().size());
        survey.getCategory().removeIf(q ->  q.getId() == category.getId() );
        System.out.println(survey.getCategory().size());
        surveyRepository.saveAndFlush(survey);

        return new RedirectView("/survey/editSurvey/" + survey.getSurveyId());
    }

    @RequestMapping (value ="deleteQuestion/{id}", method = RequestMethod.GET)
    public RedirectView deleteQuestion (Model model, Question question, HttpServletRequest request){

        Survey survey = surveyRepository.findSurveyByQuestion(question);

        survey.getQuestion().removeIf(q ->  q.getId() == question.getId() );

        surveyRepository.saveAndFlush(survey);
        return new RedirectView("/survey/editSurvey/" + survey.getSurveyId());
    }

    @RequestMapping (value ="fillQuestion/{id}", method = RequestMethod.GET)
    public String fillQuestion (Model model, Question question, HttpServletRequest request){
        Question q = questionRepository.findQuestionById(question.getId());
        Survey survey = surveyRepository.findSurveyByQuestion(q);
        model.addAttribute("question",q);
        model.addAttribute("options", q.getOption());
        model.addAttribute("categories",survey.getCategory());
        for(Option o:  q.getOption()){
            System.out.println(o.getCategoryName());
        }
        return "Survey/fillQuestion";
    }

    @RequestMapping (value ="editOption", method = RequestMethod.POST)
    public RedirectView editOption (Model model,  Option option, HttpServletRequest request){
        option.setCategory(categoryRepo.findById(Long.parseLong( option.getProxycategoryId())));
        optionRepository.save(option);
        return new RedirectView( "/survey/fillQuestion/" + questionRepository.findByOption(option).getId());
    }




    @ModelAttribute (value = "categories")
    public List<Category> newCategories(){
        return  new ArrayList<Category>();
    }

    @ModelAttribute (value = "OptionList")
    public List<Option> newOptions(){
        return  new ArrayList<Option>();
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


    @ModelAttribute (value = "Option")
    public Option newOption(){
        return  new Option();
    }


}
