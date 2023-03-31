package com.example.livraria.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LivroDTO {
    private Long id;
    @NotBlank(message="'nome' do 'livro' deve conter algum valor")
    private String nome;
    @Size(max=13,message="Tamanho do 'isbn' do 'livro' está ácima do permitido (máximo de 13 caracteres)")
    @NotBlank(message="'isbn' do 'livro' deve conter algum valor")
    private String isbn;
    private CategoriaDTO categoria;
    private EditoraDTO editora;
}
