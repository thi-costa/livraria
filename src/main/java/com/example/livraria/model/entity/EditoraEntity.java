package com.example.livraria.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "editora")
public class EditoraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false, unique = true)
    @Size(max = 255, message = "O 'nome' da 'editora' deve ter no máximo 255 caracteres")
    private String nome;
    @Column(name = "descricao")
    private  String descricao;
    @OneToMany(mappedBy = "editora")
    private List<LivroEntity> livros;
    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;
}
