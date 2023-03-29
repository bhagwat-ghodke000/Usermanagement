package com.example.UserManagement;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserManagement2Application {

	public static void main(String[] args) {

		SpringApplication.run(UserManagement2Application.class, args);
	}



	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();

	}

}
