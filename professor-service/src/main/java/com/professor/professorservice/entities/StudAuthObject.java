package com.professor.professorservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

/**
 * This class represents an object containing a student's authentication credentials.
 * It is used to parse the professor.professorService.studLogin and professor.professorService.studPassword
 * properties from the application.properties file.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudAuthObject {
    private String username;
    private String password;
}
