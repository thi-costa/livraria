package com.example.livraria.repository;

import com.example.livraria.model.entity.LivroEntity;
import com.example.livraria.model.entity.LivroFavoritoEntity;
import com.example.livraria.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroFavoritoRepository extends JpaRepository<LivroFavoritoEntity, Long> {
    List<LivroFavoritoEntity> findByUsuario(UsuarioEntity usuario);
    List<LivroFavoritoEntity> findByUsuarioAndLivro(UsuarioEntity usuario, LivroEntity livro);
}
