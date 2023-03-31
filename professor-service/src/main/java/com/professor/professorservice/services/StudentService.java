package com.professor.professorservice.services;

import com.professor.professorservice.entities.StudAuthObject;
import com.professor.professorservice.entities.Student;
import com.professor.professorservice.payloads.TokenResponse;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class StudentService implements IStudentService{

    @Value("${professor.professorService.studLogin}")
    private String studLogin;

    @Value("${professor.professorService.studPassword}")
    private String studPassword;

    private String token;

    public StudAuthObject createAuthObject(){
        return new StudAuthObject(studLogin,studPassword);
    }

    @Override
    public String getToken(StudAuthObject authBody) {
        log.info("fetching accessToken...");
        log.info("current autBody: {}",authBody.toString());
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String credentials = "student-client:secret";
        byte[] credentialsBytes = credentials.getBytes();
        byte[] base64CredentialsBytes = Base64.getEncoder().encode(credentialsBytes);
        String base64Credentials = new String(base64CredentialsBytes);
        headers.set("Authorization", "Basic " + base64Credentials);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("username", authBody.getUsername());
        map.add("password", authBody.getPassword());
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        log.info("calling remote server...");
        ResponseEntity<TokenResponse> response = restTemplate.postForEntity("http://172.16.16.138:8080/realms/students-realm/protocol/openid-connect/token", request, TokenResponse.class);
        TokenResponse tokenResponse = response.getBody();
        log.info("Token generated! {}", tokenResponse.getAccess_token());
        String token = tokenResponse.getAccess_token();
        log.info("Got token: {}", token);
        return token;
    }

    @Override
    public Student getById(String studentId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getToken(createAuthObject()));
        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<Student> response = new RestTemplate().exchange("http://172.16.16.138:8092/studentService/find/{id}", HttpMethod.GET, request, Student.class, studentId);
        Student student = response.getBody();
        log.info("Got student by ID {}: {}", studentId, student);
        return student;
    }

    @Override
    public List<Student> getAll() {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .interceptors((request, body, execution) -> {
                    request.getHeaders().setBearerAuth(getToken(createAuthObject()));
                    log.info("Request: {} {}", request.getMethod(), request.getURI());
                    log.debug("Request headers: {}", request.getHeaders());
                    return execution.execute(request, body);
                })
                .build();

        ResponseEntity<List<Student>> response = restTemplate.exchange("http://172.16.16.138:8092/studentService/all",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {});
        if (response.hasBody()) {
            List<Student> students = response.getBody();
            log.info("Response status: {}", response.getStatusCode());
            log.debug("Response headers: {}", response.getHeaders());
            log.info("Got all students: {}", students);
            return students;
        } else {
            log.warn("Response has no body");
            return Collections.emptyList();
        }
    }

    @Override
    public List<Student> getListById() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L); // Sample list of student IDs
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getToken(createAuthObject()));
        HttpEntity<List<Long>> request = new HttpEntity<>(ids, headers);
        ResponseEntity<List<Student>> response = new RestTemplate().exchange("http://172.16.16.138:8092/studentService/get/list", HttpMethod.GET, request, new ParameterizedTypeReference<List<Student>>() {});
        List<Student> students = response.getBody();
        log.info("Got students by IDs {}: {}", ids, students);
        return students;
    }
}


