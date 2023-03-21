package com.professor.professorservice.auth;

import com.professor.professorservice.entities.User;
import com.professor.professorservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class AuthController {

    @Autowired
    private final AuthenticationService authenticationService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseWrapper> register(@RequestBody User user){
        return  ResponseEntity.ok(authenticationService.register(user));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseWrapper> authenticate(@RequestBody AuthenticationRequestWrapper user){
        return ResponseEntity.ok(authenticationService.authenticate(user));
    }
}
