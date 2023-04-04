package com.serveurintermediaire.help.service;

import com.serveurintermediaire.help.model.Student;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HelpService {
    public HelpService() {
        this.webClient = WebClient.create();
    }

    private WebClient webClient;

    public List<Student> getStudents() {
        System.out.println("helpServer get All students");
        return webClient.get()
                .uri("http://localhost:8092/studentService/all")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Student>>() {
                })
                .block();
    }

    public Student getStudent(Long id) {
        System.out.println("helpServer findStudent");
        return webClient.get()
                .uri("http://localhost:8092/studentService/find/" + id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Student>() {
                })
                .block();
    }

    public List<Student> getStudentsByIds(List<Long> studentIds) {
        System.out.println("helpServer get students By Ids");
        String ids = studentIds.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
        System.out.println("ids: "+ ids);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("localhost")
                        .port(8092)
                        .path("/studentService/get/list")
                        .queryParam("studentIds", ids)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Student>>() {
                })
                .block();
    }
}
