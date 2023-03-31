package com.example.livraria.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriaDTO {
    private Long id;
    @Size(max=100, message = "Tamanho do 'nome' da 'categoria' acima do máximo  (100 caracteres)")
    @NotBlank(message = "'nome' da 'categoria' deve conter algum valor")
    private String nome;
}
