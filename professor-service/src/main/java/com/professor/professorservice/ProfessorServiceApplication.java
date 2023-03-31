package com.professor.professorservice;

import com.professor.professorservice.entities.StudAuthObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProfessorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfessorServiceApplication.class, args);
	}

}
