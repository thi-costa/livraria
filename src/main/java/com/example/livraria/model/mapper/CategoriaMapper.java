package com.example.livraria.model.mapper;


import com.example.livraria.model.dto.CategoriaDTO;
import com.example.livraria.model.entity.CategoriaEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoriaMapper {

    public CategoriaDTO update(CategoriaEntity categoria) {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(categoria.getId());
        categoriaDTO.setNome(categoria.getNome());
        return categoriaDTO;
    }

    public CategoriaEntity update(CategoriaDTO categoria) {
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setId(categoria.getId());
        categoriaEntity.setNome(categoria.getNome());
        return categoriaEntity;
    }

    public List<CategoriaEntity> updateListEntity(List<CategoriaDTO> listaDTO){
        return listaDTO.stream()
                .map(this::update)
                .toList();
    }

    public List<CategoriaDTO> updateListDTO(List<CategoriaEntity> listaEntity){
        return listaEntity.stream()
                .map(this::update)
                .toList();
    }
}
