package com.example.livraria.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LivroFavoritoDTO {
    private Long id;

    private LivroDTO livro;
    private UsuarioDTO usuario;
}
