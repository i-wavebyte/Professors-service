package org.professors.service.controller;

import org.professors.service.entities.Professor;
import org.professors.service.repositories.ProfessorRepository;
import org.professors.service.services.IProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professorService")
@CrossOrigin("*")
public class professorWebController {
    @Autowired
    IProfessorService professorService;
    @GetMapping("/get/{id}")
    public Professor getProfessor(@PathVariable Long id){
        return professorService.getProfessorById(id);
    }

    @GetMapping("/get")
    public List<Professor> getAllProfessor(){
        return professorService.getAllProfessors();
    }

    @GetMapping("/get/list")
    public List<Professor> getProfessorList(@RequestBody List<Long> ids){
        return professorService.getFromIdList(ids);
    }

    @PostMapping("/add")
    public Professor addProfessor(@RequestBody Professor professor){
        return professorService.addProfessor(professor);
    }

    @PutMapping("/update/{id}")
    public Professor addProfessor(@PathVariable Long id,@RequestBody Professor professor){
        return professorService.updateProfessor(id,professor);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProfessor(@PathVariable Long id){
        professorService.deleteProfessor(id);
    }

    @PutMapping("/assign/{id}")
    public Professor assignStudent(@PathVariable Long id,@RequestBody Long studentId){
        return professorService.assignStudent(id,studentId);
    }

    @PutMapping("/assignAll/{id}")
    public Professor assignStudent(@PathVariable Long id,@RequestBody List<Long> ids){
        return professorService.assignStudents(id,ids);
    }
}
