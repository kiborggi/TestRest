package ru.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.course.dao.Interfaces.SurveyRepository;
import ru.course.dao.SurveyDAO;
import ru.course.model.Surveys.Category;
import ru.course.model.Surveys.Option;
import ru.course.model.Surveys.Question;
import ru.course.model.Surveys.Survey;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Main {



	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);

		/*Survey survey = new Survey();
		Question question1 = new Question();

		Category categoryUp = new Category();
		Category categoryDown = new Category();
		Option optionUp = new Option();
		Option optionDown = new Option();

		categoryDown.setName("Down");
		categoryUp.setName("Up");

		List<Category> categories = new ArrayList<>();
		categories.add(categoryDown);
		categories.add(categoryUp);

		survey.setCategory(categories);

		optionDown.setCategory(categoryDown);
		optionDown.setWeight(5);

		optionUp.setCategory(categoryUp);
		optionUp.setWeight(5);

		List<Option> options = new ArrayList<>();
		options.add(optionDown);
		options.add(optionUp);

		question1.setOptionList(options);


		List<Question> questions = new ArrayList<>();
		questions.add(question1);

		survey.setQuestion(questions);

		setSurveyRepositoryave(survey);*/


	}

}
