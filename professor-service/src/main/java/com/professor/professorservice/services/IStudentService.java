package com.professor.professorservice.services;

import com.professor.professorservice.entities.StudAuthObject;
import com.professor.professorservice.entities.Student;

import java.util.List;

public interface IStudentService {
    StudAuthObject createAuthObject();
    //"http://172.16.16.138:8092/auth
    String getToken(StudAuthObject authBody);
    //"http://172.16.16.138:8092/studentService/find/{id}
    Student getById(String studentId);
    //"http://172.16.16.138:8092/studentService/all
    List<Student> getAll();
    //"http://172.16.16.138:8092/studentService/get/list (gets a list of student Ids (Long) as Request parameter)
    List<Student> getListById();
}
