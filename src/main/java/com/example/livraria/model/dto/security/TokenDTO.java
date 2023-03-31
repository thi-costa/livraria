package com.example.livraria.model.dto.security;

import lombok.Data;

@Data
public class TokenDTO {
    private String type;
    private String token;
}
