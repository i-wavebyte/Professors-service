package com.professor.professorservice.services;

import com.professor.professorservice.constants.Literals;
import com.professor.professorservice.entities.Professor;
import com.professor.professorservice.repositories.ProfessorRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Define the Professor service as a Spring service
@Transactional @Slf4j @Service
public class ProfessorService implements IProfessorService {

    // Inject the Professor repository using Spring's dependency injection
    @Autowired
    private ProfessorRepository professorRepository;

    // Implement the addProfessor method from the interface
    @Override
    public Professor addProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    // Implement the updateProfessor method from the interface
    @Override
    public Professor updateProfessor(Long prodId, Professor professor) {
        // Find the Professor by ID or throw an exception if not found
        Professor p = professorRepository.findById(prodId).orElseThrow(() -> new RuntimeException(Literals.PROFESSOR_NOT_FOUND));
        // Update the Professor fields if not null
        if(professor.getName() != null) p.setName(professor.getName());
        if(professor.getSubject() != null) p.setSubject(professor.getSubject());
        if(professor.getEmail() != null) p.setEmail(professor.getEmail());
        if(professor.getTelephone() != null) p.setTelephone(professor.getTelephone());
        // Save the updated Professor and return it
        return professorRepository.save(p);
    }

    // Implement the getAllProfessors method from the interface
    @Override
    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    // Implement the getProfessorById method from the interface
    @Override
    public Professor getProfessorById(Long profId) {
        // Find the Professor by ID or throw an exception if not found
        return professorRepository.findById(profId).orElseThrow(() -> new RuntimeException(Literals.PROFESSOR_NOT_FOUND));
    }

    // Implement the deleteProfessor method from the interface
    @Override
    public void deleteProfessor(Long profId) {
        // Find the Professor by ID or throw an exception if not found
        Professor p = professorRepository.findById(profId).orElseThrow(() -> new RuntimeException(Literals.PROFESSOR_NOT_FOUND));
        // Delete the Professor
        professorRepository.delete(p);
    }

    // Implement the getFromIdList method from the interface
    @Override
    public List<Professor> getFromIdList(List<Long> profIds) {
        List<Professor> professors = new ArrayList<>();
        for(Long id : profIds){
            // Find the Professor by ID or create a new one if not found
            Professor p = professorRepository.findById(id).orElse(new Professor());
            // If the ID is null, skip this Professor
            if(p.getId()==null) continue;
            // Add the Professor to the list
            professors.add(p);
        }
        // Return the list of Professors
        return professors;
    }

    // Implement the assignStudents method from the interface
    @Override
    public Professor assignStudents(Long profIds, List<Long> studentIds) {
        // Find the Professor by ID or throw an exception if not found
        Professor p = professorRepository.findById(profIds).orElseThrow(() -> new RuntimeException(Literals.PROFESSOR_NOT_FOUND));
        // Add the student IDs to the Professor's list of students
        p.getEtudiants().addAll(studentIds);
        // Convert the list to a set to remove duplicates
        Set<Long> set = new HashSet<>(p.getEtudiants());
        // Clear the list and add back the distinct values
        p.getEtudiants().clear();
        p.getEtudiants().addAll(set);
        // Save the updated Professor and return it
        return professorRepository.save(p);
    }

    // Implement the assignStudent method from the interface
    @Override
    public Professor assignStudent(Long idProfessor, Long idStudent) {
        // Find the Professor by ID or throw an exception if not found
        Professor p = professorRepository.findById(idProfessor).orElseThrow(() -> new RuntimeException(Literals.PROFESSOR_NOT_FOUND));
        // Add the student ID to the Professor's list of students
        p.getEtudiants().add(idStudent);
        // Convert the list to a set to remove duplicates
        Set<Long> set = new HashSet<>(p.getEtudiants());
        // Clear the list and add back the distinct values
        p.getEtudiants().clear();
        p.getEtudiants().addAll(set);
        // Save the updated Professor and return it
        return professorRepository.save(p);
    }

    @Override
    public List<Professor> addAll(List<Professor> professors) {
        return professorRepository.saveAll(professors);
    }


}
