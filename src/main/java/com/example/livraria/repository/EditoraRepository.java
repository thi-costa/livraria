package com.example.livraria.repository;

import com.example.livraria.model.entity.EditoraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepository extends JpaRepository<EditoraEntity, Long> {
}
