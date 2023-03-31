package com.example.livraria.repository;

import com.example.livraria.model.entity.CategoriaEntity;
import com.example.livraria.model.entity.LivroEntity;
import com.example.livraria.model.entity.EditoraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<LivroEntity, Long> {
    @Query("SELECT l FROM LivroEntity l "
            + "WHERE UPPER(l.nome) LIKE CONCAT('%',UPPER(:nome),'%') "
            + "OR (l.isbn = :isbn) ")
    List<LivroEntity> findByNomeOrIsbn(String nome, @Param("isbn") String isbn);
    List<LivroEntity> findByCategoria(CategoriaEntity categoria);
    List<LivroEntity> findByEditora(EditoraEntity editora);
}
