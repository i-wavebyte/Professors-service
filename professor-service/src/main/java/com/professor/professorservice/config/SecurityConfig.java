package com.professor.professorservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors() // Enable CORS
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/professorService/**") // Restrict POST requests
                .hasAnyAuthority("PROF_MANAGER")
                .requestMatchers(HttpMethod.GET, "/professorService/**") // Restrict POST requests
                .hasAnyAuthority("PROF_MANAGER","STUD_MANAGER")
                .requestMatchers(HttpMethod.GET, "/students/**") // Restrict POST requests
                .hasAnyAuthority("PROF_MANAGER","STUD_MANAGER")
                .requestMatchers(HttpMethod.PUT, "/professorService/**") // Restrict POST requests
                .hasAnyAuthority("PROF_MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/professorService/**") // Restrict POST requests
                .hasAnyAuthority("PROF_MANAGER")
                .requestMatchers("/delete/**")
                .hasAnyAuthority("PROF_MANAGER")
                .requestMatchers("/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // Set allowed origins
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Set allowed methods
        configuration.setAllowedHeaders(Arrays.asList("*")); // Set allowed headers
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
