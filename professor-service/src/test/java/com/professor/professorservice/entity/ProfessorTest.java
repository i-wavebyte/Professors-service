package com.professor.professorservice.entity;
import com.professor.professorservice.entities.Professor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Professor.class})
@RunWith(MockitoJUnitRunner.class)
class ProfessorTest {

    private Professor professor;

    @BeforeEach
    public void setUp() {
        professor = new Professor();
    }

    @Test
    void testEtudiantsFieldIsEmpty() {
        List<Long> etudiants = professor.getEtudiants();
        assertTrue(etudiants.isEmpty());
    }

    @Test
    void testEtudiantsFieldAdd() {
        Long etudiantId = 1L;
        professor.getEtudiants().add(etudiantId);
        List<Long> etudiants = professor.getEtudiants();
        assertEquals(1, etudiants.size());
        assertTrue(etudiants.contains(etudiantId));
    }

    @Test
    void testEtudiantsFieldRemove() {
        Long etudiantId = 1L;
        professor.getEtudiants().add(etudiantId);
        assertTrue(professor.getEtudiants().contains(etudiantId));
        professor.getEtudiants().remove(etudiantId);
        assertFalse(professor.getEtudiants().contains(etudiantId));
    }
}