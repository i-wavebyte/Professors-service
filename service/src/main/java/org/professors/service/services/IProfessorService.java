package org.professors.service.services;

import org.professors.service.entities.Professor;

import java.util.List;

public interface IProfessorService {
    Professor addProfessor(Professor professor);
    Professor updateProfessor(Long id, Professor professor);
    List<Professor> getAllProfessors();
    Professor getProfessorById(Long id);
    void deleteProfessor(Long id);
    List<Professor> getFromIdList(List<Long> ids);
    Professor assignStudents(Long id, List<Long> ids);
    Professor assignStudent(Long id, Long student);
}
