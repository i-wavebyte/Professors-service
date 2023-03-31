package com.professor.professorservice.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class TokenResponse {
    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String token_type;
}
