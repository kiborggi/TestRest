package ru.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.course.dao.products.DAO_Factory;
import ru.course.dao.products.interfaces.I_DetailedOrdersDAO;
import ru.course.model.DetailedOrders;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

}
