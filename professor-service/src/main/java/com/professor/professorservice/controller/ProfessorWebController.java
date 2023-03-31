package com.professor.professorservice.controller;

import com.professor.professorservice.entities.Professor;
import com.professor.professorservice.services.IProfessorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Define a REST controller for Professor service
@RestController
// Map the base URL for the service
@RequestMapping("/professorService")
// Allow cross-origin requests from any domain
@CrossOrigin("http://localhost:4200")
public class ProfessorWebController {

    // Inject the Professor service using Spring's dependency injection

    private IProfessorService professorService;
    public ProfessorWebController(IProfessorService professorService){
        this.professorService = professorService;
    }

    // Handle GET requests for a specific Professor by ID
    @GetMapping("/get/{profId}")
    public Professor getProfessor(@PathVariable long profId){
        return professorService.getProfessorById(profId);
    }

    // Handle GET requests for all Professors
    @GetMapping("/get")
    public List<Professor> getAllProfessor(){
        return professorService.getAllProfessors();
    }

    // Handle GET requests for Professors by a list of IDs
    @GetMapping("/get/list")
    public List<Professor> getProfessorList(@RequestParam List<Long> profIds){
        return professorService.getFromIdList(profIds);
    }

    // Handle POST requests to add a new Professor
    @PostMapping("/add")
    public Professor addProfessor(@RequestBody Professor professor){
        return professorService.addProfessor(professor);
    }

    // Handle PUT requests to update an existing Professor by ID
    @PutMapping("/update/{profId}")
    public Professor updateProfessor(@PathVariable Long profId,@RequestBody Professor professor){
        return professorService.updateProfessor(profId,professor);
    }

    // Handle DELETE requests to delete an existing Professor by ID
    @DeleteMapping("/delete/{profId}")
    public void deleteProfessor(@PathVariable Long profId){
        professorService.deleteProfessor(profId);
    }

    // Handle PUT requests to assign a Student to a Professor by ID
    @PutMapping("/assign/{profId}")
    public Professor assignStudent(@PathVariable Long profId,@RequestParam Long studentId){
        return professorService.assignStudent(profId,studentId);
    }

    // Handle PUT requests to assign multiple Students to a Professor by ID
    @PutMapping("/assignAll/{id}")
    public Professor assignStudent(@PathVariable Long profId,@RequestBody List<Long> studentIds){
        return professorService.assignStudents(profId,studentIds);
    }

    // Handle POST requests to add multiple Professors
    @PostMapping("/addAll")
    public List<Professor> addAll(@RequestBody List<Professor> professors){
        return professorService.addAll(professors);
    }
}