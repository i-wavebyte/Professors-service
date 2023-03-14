package org.professors.service.services;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.professors.service.entities.Professor;
import org.professors.service.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional @Slf4j @Service
public class ProfessorService implements IProfessorService{

    @Autowired
    private ProfessorRepository professorRepository;
    @Override
    public Professor addProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public Professor updateProfessor(Long id, Professor professor) {
        Professor p = professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Professor not found"));
        if(professor.getName() != null) p.setName(professor.getName());
        if(professor.getSubject() != null) p.setSubject(professor.getSubject());
        if(professor.getEmail() != null) p.setEmail(professor.getEmail());
        if(professor.getTelephone() != null) p.setTelephone(professor.getTelephone());

        return professorRepository.save(p);
    }

    @Override
    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    @Override
    public Professor getProfessorById(Long id) {
        return professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Professor not found"));
    }

    @Override
    public void deleteProfessor(Long id) {
        Professor p = professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Professor not found"));
        professorRepository.delete(p);
    }

    @Override
    public List<Professor> getFromIdList(List<Long> ids) {
        List<Professor> Professors = new ArrayList<Professor>();

        for(Long id : ids){
            Professor p = professorRepository.findById(id).orElse(new Professor());
            if(p.getId()==null) continue;
            Professors.add(p);
        }

        return Professors;
    }

    @Override
    public Professor assignStudents(Long id, List<Long> ids) {
        Professor p = professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Professor not found"));
        p.getEtudiants().addAll(ids);
        Set<Long> set = new HashSet<>(p.getEtudiants()); // Convert list to Set to remove duplicates
        p.getEtudiants().clear();
        p.getEtudiants().addAll(set); // Add the distinct values back to the list
        return professorRepository.save(p);
    }

    @Override
    public Professor assignStudent(Long id, Long student) {
        Professor p = professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Professor not found"));
        p.getEtudiants().add(student);
        Set<Long> set = new HashSet<>(p.getEtudiants()); // Convert list to Set to remove duplicates
        p.getEtudiants().clear();
        p.getEtudiants().addAll(set); // Add the distinct values back to the list
        return professorRepository.save(p);
    }


}
