package ru.course.dao.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.course.model.Surveys.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long > {
}
