package com.professor.professorservice.controller;

import com.professor.professorservice.entities.Professor;
import com.professor.professorservice.repositories.ProfessorRepository;
import com.professor.professorservice.services.IProfessorService;
import com.professor.professorservice.services.ProfessorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ProfessorWebController.class})
@RunWith(MockitoJUnitRunner.class)
class ProfessorWebControllerTests {


    @MockBean
    private IProfessorService professorService;

    @InjectMocks
    @Autowired
    private ProfessorWebController controller;

    @Test
    void testAddProfessor(){
        // Set up mock behavior for the addProfessor method
        Professor professor = createProfessor();
        when(professorService.addProfessor(professor)).thenReturn(professor);
        // Call the method under test
        Professor result = controller.addProfessor(professor);
        // Verify the result
        assertEquals("hajib diae", result.getName());
    }

    @Test
    void testAssignStudent(){
        // Set up mock behavior for the addProfessor method
        Professor professor = createProfessor();
        professor.getEtudiants().add(2L);
        when(professorService.assignStudent(1L,2L)).thenReturn(professor);
        // Call the method under test
        Professor result = controller.assignStudent(1L,2L);
        // Verify the result
        List<Long> studentsForTest = new ArrayList<>();
        studentsForTest.add(2L);
        assertEquals(studentsForTest, result.getEtudiants());
    }

    private Professor createProfessor() {
        return Professor.builder()
                .email("diae.hajib@gmail.com")
                .name("hajib diae")
                .telephone("0636949549")
                .subject("C++")
                .etudiants(new ArrayList<Long>())
                .build();
    }
}