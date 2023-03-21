package com.professor.professorservice.controller;

import com.professor.professorservice.entities.Professor;
import com.professor.professorservice.services.IProfessorService;
import com.professor.professorservice.services.ProfessorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorWebControllerTests {


    IProfessorService professorService =new ProfessorService();
    @Test
    void addProfessor(){
//        Professor professor = Professor.builder()
//                .email("diae.hajib@gmail.com")
//                .name("hajib diae")
//                .telephone("0636949549")
//                .subject("C++")
//                .build();
//        ProfessorWebController controller = new ProfessorWebController(professorService);
//        Professor result = controller.addProfessor(professor);
//        assertEquals("hajib diae",professor.getName());
    }


}
