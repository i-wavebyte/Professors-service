package com.professor.professorservice.services;


import com.professor.professorservice.entities.Professor;

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
    List<Professor> addAll(List<Professor> professors);
}