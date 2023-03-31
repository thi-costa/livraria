package com.example.livraria.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EditoraDTO {
    private Long id;
    @Size(max=255, message = "Tamanho do 'nome' da 'editora' acima do máximo  (255 caracteres)")
    @NotBlank(message = "'nome' da 'editora' deve conter algum valor")
    private String nome;
    private String descricao;
}
