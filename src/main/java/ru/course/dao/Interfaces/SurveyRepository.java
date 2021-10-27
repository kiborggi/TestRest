package ru.course.dao.Interfaces;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.course.model.Surveys.Category;
import ru.course.model.Surveys.Question;
import ru.course.model.Surveys.Survey;

import java.util.List;

@Repository
public interface SurveyRepository extends CrudRepository<Survey, Long > {

    public Survey saveAndFlush(Survey s);
    public List<Survey> findAllByUserId(long userId);
    public  Survey findBySurveyId(long surveyId);
    public Survey findSurveyByQuestion(Question q);
    public Survey findSurveyByCategory(Category c);

}
