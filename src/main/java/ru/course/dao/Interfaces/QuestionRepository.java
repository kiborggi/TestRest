package ru.course.dao.Interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.course.model.Surveys.Option;
import ru.course.model.Surveys.Question;
import ru.course.model.Surveys.Survey;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long > {

 public Question findQuestionById(long id);
 public Question findByOption(Option option);


}