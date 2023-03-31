package com.example.livraria.model.dto.security;

import com.example.livraria.model.dto.UsuarioDTO;
import lombok.Data;

@Data
public class UsuarioComSenhaDTO extends UsuarioDTO {
    private String password;
}
