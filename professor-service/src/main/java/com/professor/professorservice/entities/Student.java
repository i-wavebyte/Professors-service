package com.professor.professorservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class Student {
    private Long id;
    private String name;
    private String email;
    private String telephone;
    private String cin;
    private String groupe;
    private List<Long> professors = new ArrayList<>();
}