package com.example.livraria.model.mapper;
;
import com.example.livraria.model.dto.EditoraDTO;
import com.example.livraria.model.entity.EditoraEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EditoraMapper {
    public EditoraDTO update(EditoraEntity editora) {
        EditoraDTO editoraDTO = new EditoraDTO();
        editoraDTO.setId(editora.getId());
        editoraDTO.setNome(editora.getNome());
        editoraDTO.setDescricao(editora.getDescricao());
        return editoraDTO;
    }

    public EditoraEntity update(EditoraDTO editora) {
        EditoraEntity editoraEntity = new EditoraEntity();
        editoraEntity.setId(editora.getId());
        editoraEntity.setNome(editora.getNome());
        editoraEntity.setDescricao(editora.getDescricao());
        return editoraEntity;
    }

    public List<EditoraEntity> updateListEntity(List<EditoraDTO> listaDTO){
        return listaDTO.stream()
                .map(this::update)
                .toList();
    }

    public List<EditoraDTO> updateListDTO(List<EditoraEntity> listaEntity){
        return listaEntity.stream()
                .map(this::update)
                .toList();
    }
}
