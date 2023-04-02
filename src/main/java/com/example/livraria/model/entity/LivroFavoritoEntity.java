package com.example.livraria.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "livro_favorito")
public class LivroFavoritoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "livro", nullable = false)
    private LivroEntity livro;
    @ManyToOne
    @JoinColumn(name = "usuario", nullable = false)
    private UsuarioEntity usuario;

    // Adicionado limitação de que um usuário só pode ter o mesmo livro favoritado 1 vez
    @Column(unique=true)
    private String combinacaoLivroUsuario;

    @PrePersist
    private void setCombinacaoLivroUsuario(){
        this.combinacaoLivroUsuario =  livro.getId()+ ":" + usuario.getId();
    }

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;
}
