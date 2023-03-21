package com.professor.professorservice.auth;

import com.professor.professorservice.config.JwtService;
import com.professor.professorservice.entities.User;
import com.professor.professorservice.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service @Slf4j
public class AuthenticationService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponseWrapper register(User user) {

        User u = User.builder()
                .phone(user.getPhone())
                .email(user.getEmail())
                .role(user.getRole())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();
        userRepository.save(u);
        String jwtToken = jwtService.generateToken(u);

        return new AuthenticationResponseWrapper(jwtToken);

    }

    public AuthenticationResponseWrapper authenticate(AuthenticationRequestWrapper user) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        User u = userRepository.findByEmail(user.getUsername()).orElseThrow(()->new UsernameNotFoundException("user not found"));
        String jwtToken = jwtService.generateToken(u);
        return new AuthenticationResponseWrapper(jwtToken);
    }
}
