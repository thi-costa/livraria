package com.example.livraria.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "livro")
public class LivroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "isbn", nullable = false)
    @Size(max = 13, message = "O 'isbn' do 'livro' só pode ter no máximo 13 caracteres")
    private String isbn;
    @ManyToOne
    @JoinColumn(name = "editora", nullable = false)
    private EditoraEntity editora;
    @ManyToOne
    @JoinColumn(name = "categoria", nullable = false)
    private CategoriaEntity categoria;
    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;
}
