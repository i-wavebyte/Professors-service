package com.professor.professorservice.service;
import com.professor.professorservice.constants.Literals;
import com.professor.professorservice.entities.Professor;
import com.professor.professorservice.repositories.ProfessorRepository;
import com.professor.professorservice.services.ProfessorService;
import org.junit.jupiter.api.BeforeEach;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ProfessorService.class})
@RunWith(MockitoJUnitRunner.class)
class ProfessorServiceTest {
    //codegpt do: comment this code following best practices
    @MockBean
    private ProfessorRepository professorRepository;

    @InjectMocks
    @Autowired
    private ProfessorService professorService;

    private Professor professor;
    private Long professorId;

    @BeforeEach
    public void setUp() {
        professor = new Professor();
        professorId = 1L;
        professor.setId(professorId);
        professor.setName("John Doe");
        professor.setSubject("Mathematics");
        professor.setEmail("johndoe@example.com");
        professor.setTelephone("555-1234");
    }

    @Test
    void testUpdateProfessor() {
        String updatedName = "Jane Smith";
        String updatedSubject = "Physics";
        String updatedEmail = "janesmith@example.com";
        String updatedTelephone = "555-5678";
        Professor updatedProfessor = new Professor();
        updatedProfessor.setName(updatedName);
        updatedProfessor.setSubject(updatedSubject);
        updatedProfessor.setEmail(updatedEmail);
        updatedProfessor.setTelephone(updatedTelephone);

        when(professorRepository.findById(professorId)).thenReturn(Optional.ofNullable(professor));
        when(professorRepository.save(any(Professor.class))).thenReturn(updatedProfessor);

        Professor result = professorService.updateProfessor(professorId, updatedProfessor);

        verify(professorRepository, times(1)).findById(professorId);
        verify(professorRepository, times(1)).save(any(Professor.class));

        assertEquals(updatedName, result.getName());
        assertEquals(updatedSubject, result.getSubject());
        assertEquals(updatedEmail, result.getEmail());
        assertEquals(updatedTelephone, result.getTelephone());
    }

    @Test
    void testUpdateProfessorNotFound() {
        Long nonExistentId = 2L;
        Professor nonExistentProfessor = new Professor();
        nonExistentProfessor.setId(nonExistentId);

        when(professorRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> professorService.updateProfessor(nonExistentId, nonExistentProfessor));

        verify(professorRepository, times(1)).findById(nonExistentId);
        verify(professorRepository, times(0)).save(any(Professor.class));

        assertEquals(Literals.PROFESSOR_NOT_FOUND, exception.getMessage());
    }

    @Test
    void testAddProfessor() {
        Professor professor = new Professor();
        professor.setName("John Doe");
        professor.setSubject("Mathematics");
        professor.setEmail("johndoe@example.com");
        professor.setTelephone("555-1234");

        Professor savedProfessor = new Professor();
        savedProfessor.setId(1L);
        savedProfessor.setName(professor.getName());
        savedProfessor.setSubject(professor.getSubject());
        savedProfessor.setEmail(professor.getEmail());
        savedProfessor.setTelephone(professor.getTelephone());

        when(professorRepository.save(any(Professor.class))).thenReturn(savedProfessor);

        Professor result = professorService.addProfessor(professor);

        verify(professorRepository, times(1)).save(any(Professor.class));

        assertNotNull(result.getId());
        assertEquals(savedProfessor.getName(), result.getName());
        assertEquals(savedProfessor.getSubject(), result.getSubject());
        assertEquals(savedProfessor.getEmail(), result.getEmail());
        assertEquals(savedProfessor.getTelephone(), result.getTelephone());
    }

    @Test
    void testGetAllProfessors() {
        Professor professor1 = new Professor();
        professor1.setId(1L);
        professor1.setName("John Doe");
        professor1.setSubject("Mathematics");
        professor1.setEmail("johndoe@example.com");
        professor1.setTelephone("555-1234");

        Professor professor2 = new Professor();
        professor2.setId(2L);
        professor2.setName("Jane Smith");
        professor2.setSubject("Physics");
        professor2.setEmail("janesmith@example.com");
        professor2.setTelephone("555-5678");

        List<Professor> professors = new ArrayList<>();
        professors.add(professor1);
        professors.add(professor2);

        when(professorRepository.findAll()).thenReturn(professors);

        List<Professor> result = professorService.getAllProfessors();

        verify(professorRepository, times(1)).findAll();

        assertEquals(2, result.size());
        assertEquals(professor1.getId(), result.get(0).getId());
        assertEquals(professor1.getName(), result.get(0).getName());
        assertEquals(professor1.getSubject(), result.get(0).getSubject());
        assertEquals(professor1.getEmail(), result.get(0).getEmail());
        assertEquals(professor1.getTelephone(), result.get(0).getTelephone());
        assertEquals(professor2.getId(), result.get(1).getId());
        assertEquals(professor2.getName(), result.get(1).getName());
        assertEquals(professor2.getSubject(), result.get(1).getSubject());
        assertEquals(professor2.getEmail(), result.get(1).getEmail());
        assertEquals(professor2.getTelephone(), result.get(1).getTelephone());
    }

    @Test
    void testGetProfessorById() {
        Long professorId = 1L;
        Professor professor = new Professor();
        professor.setId(professorId);
        professor.setName("John Doe");
        professor.setSubject("Mathematics");
        professor.setEmail("johndoe@example.com");
        professor.setTelephone("555-1234");

        when(professorRepository.findById(professorId)).thenReturn(Optional.of(professor));

        Professor result = professorService.getProfessorById(professorId);

        verify(professorRepository, times(1)).findById(professorId);

        assertNotNull(result);
        assertEquals(professorId, result.getId());
        assertEquals(professor.getName(), result.getName());
        assertEquals(professor.getSubject(), result.getSubject());
        assertEquals(professor.getEmail(), result.getEmail());
        assertEquals(professor.getTelephone(), result.getTelephone());
    }

    @Test
    void testDeleteProfessor() {
        Long professorId = 1L;
        Professor professor = new Professor();
        professor.setId(professorId);
        professor.setName("John Doe");
        professor.setSubject("Mathematics");
        professor.setEmail("johndoe@example.com");
        professor.setTelephone("555-1234");

        when(professorRepository.findById(professorId)).thenReturn(Optional.of(professor));

        professorService.deleteProfessor(professorId);

        verify(professorRepository, times(1)).findById(professorId);
        verify(professorRepository, times(1)).delete(professor);
    }

    @Test
    void testGetFromIdList() {
        Long professorId1 = 1L;
        Professor professor1 = new Professor();
        professor1.setId(professorId1);
        professor1.setName("John Doe");
        professor1.setSubject("Mathematics");
        professor1.setEmail("johndoe@example.com");
        professor1.setTelephone("555-1234");

        Long professorId2 = 2L;
        Professor professor2 = new Professor();
        professor2.setId(professorId2);
        professor2.setName("Jane Smith");
        professor2.setSubject("Physics");
        professor2.setEmail("janesmith@example.com");
        professor2.setTelephone("555-5678");

        List<Long> professorIds = new ArrayList<>();
        professorIds.add(professorId1);
        professorIds.add(professorId2);

        when(professorRepository.findById(professorId1)).thenReturn(Optional.of(professor1));
        when(professorRepository.findById(professorId2)).thenReturn(Optional.of(professor2));

        List<Professor> result = professorService.getFromIdList(professorIds);

        verify(professorRepository, times(1)).findById(professorId1);
        verify(professorRepository, times(1)).findById(professorId2);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(professorId1, result.get(0).getId());
        assertEquals(professor1.getName(), result.get(0).getName());
        assertEquals(professor1.getSubject(), result.get(0).getSubject());
        assertEquals(professor1.getEmail(), result.get(0).getEmail());
        assertEquals(professor1.getTelephone(), result.get(0).getTelephone());
        assertEquals(professorId2, result.get(1).getId());
        assertEquals(professor2.getName(), result.get(1).getName());
        assertEquals(professor2.getSubject(), result.get(1).getSubject());
        assertEquals(professor2.getEmail(), result.get(1).getEmail());
        assertEquals(professor2.getTelephone(), result.get(1).getTelephone());
    }

    @Test
    void testAssignStudents() {
        Long professorId = 1L;
        Professor professor = new Professor();
        professor.setId(professorId);
        professor.setName("John Doe");
        professor.setSubject("Mathematics");
        professor.setEmail("johndoe@example.com");
        professor.setTelephone("555-1234");

        List<Long> studentIds = new ArrayList<>();
        studentIds.add(1L);
        studentIds.add(2L);

        when(professorRepository.findById(professorId)).thenReturn(Optional.of(professor));
        when(professorRepository.save(professor)).thenReturn(professor);

        Professor result = professorService.assignStudents(professorId, studentIds);

        verify(professorRepository, times(1)).findById(professorId);
        verify(professorRepository, times(1)).save(professor);

        assertNotNull(result);
        assertEquals(professorId, result.getId());
        assertEquals(professor.getName(), result.getName());
        assertEquals(professor.getSubject(), result.getSubject());
        assertEquals(professor.getEmail(), result.getEmail());
        assertEquals(professor.getTelephone(), result.getTelephone());
        assertEquals(studentIds, result.getEtudiants());
    }

    @Test
    void testAssignStudent() {
        Long professorId = 1L;
        Professor professor = new Professor();
        professor.setId(professorId);
        professor.setName("John Doe");
        professor.setSubject("Mathematics");
        professor.setEmail("johndoe@example.com");
        professor.setTelephone("555-1234");

        Long studentId = 1L;

        when(professorRepository.findById(professorId)).thenReturn(Optional.of(professor));
        when(professorRepository.save(professor)).thenReturn(professor);

        Professor result = professorService.assignStudent(professorId, studentId);

        verify(professorRepository, times(1)).findById(professorId);
        verify(professorRepository, times(1)).save(professor);

        assertNotNull(result);
        assertEquals(professorId, result.getId());
        assertEquals(professor.getName(), result.getName());
        assertEquals(professor.getSubject(), result.getSubject());
        assertEquals(professor.getEmail(), result.getEmail());
        assertEquals(professor.getTelephone(), result.getTelephone());
        assertTrue(result.getEtudiants().contains(studentId));
    }

    @Test
    void testAddAll() {
        Long professorId1 = 1L;
        Professor professor1 = new Professor();
        professor1.setId(professorId1);
        professor1.setName("John Doe");
        professor1.setSubject("Mathematics");
        professor1.setEmail("johndoe@example.com");
        professor1.setTelephone("555-1234");

        Long professorId2 = 2L;
        Professor professor2 = new Professor();
        professor2.setId(professorId2);
        professor2.setName("Jane Smith");
        professor2.setSubject("Physics");
        professor2.setEmail("janesmith@example.com");
        professor2.setTelephone("555-5678");

        List<Professor> professors = new ArrayList<>();
        professors.add(professor1);
        professors.add(professor2);

        when(professorRepository.saveAll(professors)).thenReturn(professors);

        List<Professor> result = professorService.addAll(professors);

        verify(professorRepository, times(1)).saveAll(professors);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(professorId1, result.get(0).getId());
        assertEquals(professor1.getName(), result.get(0).getName());
        assertEquals(professor1.getSubject(), result.get(0).getSubject());
        assertEquals(professor1.getEmail(), result.get(0).getEmail());
        assertEquals(professor1.getTelephone(), result.get(0).getTelephone());
        assertEquals(professorId2, result.get(1).getId());
        assertEquals(professor2.getName(), result.get(1).getName());
        assertEquals(professor2.getSubject(), result.get(1).getSubject());
        assertEquals(professor2.getEmail(), result.get(1).getEmail());
        assertEquals(professor2.getTelephone(), result.get(1).getTelephone());
    }
}