package com.professor.professorservice.repositories;


import com.professor.professorservice.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor,Long> {
    
}
