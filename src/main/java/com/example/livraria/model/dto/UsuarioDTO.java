package com.example.livraria.model.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
public class UsuarioDTO {
    private Long id;
    private String email;
    private String nome;
    private String username;
    private String cpf;


}
