package com.serveurintermediaire.help.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private Long id;
    private String name;
    private String email;
    private String telephone;
    private String cin;
    private String groupe;
    private List<Long> professors;
}
