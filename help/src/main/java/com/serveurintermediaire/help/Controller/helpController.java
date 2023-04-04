package com.serveurintermediaire.help.Controller;

import com.serveurintermediaire.help.model.Student;
import com.serveurintermediaire.help.service.HelpService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/helpServer")
@CrossOrigin(origins = "*")
public class helpController {

private final HelpService helpService;
    public helpController(HelpService helpService) {
        this.helpService = helpService;
    }

    @GetMapping("/all")
    public List<Student> getStudents(){
        List<Student> a = helpService.getStudents();
        System.out.println(a);
        return a;
    }

    @GetMapping("/find/{id}")
    public Student getStudent(@PathVariable("id") Long id){
        Student a = helpService.getStudent(id);
        System.out.println(a);
        return a;
    }


    @GetMapping("/get/list")
    public List<Student> getStudentsByIds(@RequestParam List<Long> studentIds){
        List<Student> a = helpService.getStudentsByIds(studentIds);
        System.out.println(a);
        return a;
    }
}
