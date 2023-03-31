package com.example.livraria.model.mapper;

import com.example.livraria.model.entity.LivroEntity;
import com.example.livraria.model.dto.LivroDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LivroMapper {
    private CategoriaMapper categoriaMapper = new CategoriaMapper();
    private EditoraMapper editoraMapper = new EditoraMapper();
    public LivroDTO update(LivroEntity livro) {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setId(livro.getId());
        livroDTO.setNome(livro.getNome());
        livroDTO.setIsbn(livro.getIsbn());
        livroDTO.setCategoria(categoriaMapper.update(livro.getCategoria()));
        livroDTO.setEditora(editoraMapper.update(livro.getEditora()));
        return livroDTO;
    }

    public LivroEntity update(LivroDTO livro) {
        LivroEntity livroEntity = new LivroEntity();
        livroEntity.setId(livro.getId());
        livroEntity.setNome(livro.getNome());
        livroEntity.setIsbn(livro.getIsbn());
        livroEntity.setCategoria(categoriaMapper.update(livro.getCategoria()));
        livroEntity.setEditora(editoraMapper.update(livro.getEditora()));
        return livroEntity;
    }

    public List<LivroEntity> updateListEntity(List<LivroDTO> listaDTO){
        return listaDTO.stream()
                .map(this::update)
                .toList();
    }

    public List<LivroDTO> updateListDTO(List<LivroEntity> listaEntity){
        return listaEntity.stream()
                .map(this::update)
                .toList();
    }
}
