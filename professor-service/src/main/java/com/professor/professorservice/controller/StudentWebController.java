package com.professor.professorservice.controller;

import com.professor.professorservice.entities.Student;
import com.professor.professorservice.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentWebController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable String studentId) {
        return studentService.getById(studentId);
    }

    @GetMapping("/")
    public List<Student> getAllStudents() {
        return studentService.getAll();
    }

    @GetMapping("/list")
    public List<Student> getStudentsByIdList() {
        return studentService.getListById();
    }
}
