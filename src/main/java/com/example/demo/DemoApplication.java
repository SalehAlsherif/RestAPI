package com.example.demo;

import com.example.demo.controllers.PhoneNumbersController;
import database.DataBase;
import models.CountryPhoneTemplate;
import models.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.HashMap;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {


	//Spring Boot will automagically wire this object using application.properties:
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PhoneNumbersController.countriesTemplates=new HashMap<String, CountryPhoneTemplate>();
		PhoneNumbersController.countriesTemplates.put("Cameroon",new CountryPhoneTemplate("Cameroon","\\(237\\)\\ ?[2368]\\d{7,8}$","\\(237\\)\\ .*"));
		PhoneNumbersController.countriesTemplates.put("Ethiopia",new CountryPhoneTemplate("Ethiopia","\\(251\\)\\ ?[1-59]\\d{8}$","\\(251\\)\\ .*"));
		PhoneNumbersController.countriesTemplates.put("Morocco",new CountryPhoneTemplate("Morocco","\\(212\\)\\ ?[5-9]\\d{8}$","\\(212\\)\\ .*"));
		PhoneNumbersController.countriesTemplates.put("Mozambique",new CountryPhoneTemplate("Mozambique","\\(258\\)\\ ?[28]\\d{7,8}$","\\(258\\)\\ .*"));
		PhoneNumbersController.countriesTemplates.put("Uganda",new CountryPhoneTemplate("Uganda","\\(256\\)\\ ?\\d{9}$","\\(256\\)\\ .*"));
		DataBase.setJdbcTemplate(jdbcTemplate);
	}

}
