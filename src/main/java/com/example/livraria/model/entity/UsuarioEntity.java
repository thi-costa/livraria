package com.example.livraria.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioEntity implements UserDetails {
    // Java Serializable
    private static final long seralVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String email;
    private String nome;
    @Column(unique = true, nullable = false)
    private String username;
    private String cpf;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "usuario")
    private List<LivroFavoritoEntity> livrosFavoritos;

    @ManyToOne
    @JoinColumn
    private PerfilEntity perfil;


    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(perfil != null){
            return List.of(perfil);
        }
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
