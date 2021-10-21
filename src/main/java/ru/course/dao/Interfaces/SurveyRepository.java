package ru.course.dao.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.course.model.Surveys.Survey;
@Repository
public interface SurveyRepository extends CrudRepository<Survey, Long > {

    public Survey saveAndFlush(Survey s);
}
