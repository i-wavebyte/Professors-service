package com.redacode.redacode.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String email;
    private String subject;
    private String phone;

    @ElementCollection

    private List<Long> professors = new ArrayList<Long>();


    public Student(){

    }

    public Student(String name, String email, String subject, String Phone, String imageUrl, List<Long> professors){
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.phone = phone;
        this.professors = professors;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public List<Long> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Long> professors) {
        this.professors = professors;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", subject='" + subject + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
