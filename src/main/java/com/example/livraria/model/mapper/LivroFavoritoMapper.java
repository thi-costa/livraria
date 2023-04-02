package com.example.livraria.model.mapper;

import com.example.livraria.model.dto.LivroDTO;
import com.example.livraria.model.dto.LivroFavoritoDTO;
import com.example.livraria.model.entity.LivroEntity;
import com.example.livraria.model.entity.LivroFavoritoEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LivroFavoritoMapper {
    private LivroMapper livroMapper = new LivroMapper();
    private UsuarioSemSenhaMapper usuarioMapper = new UsuarioSemSenhaMapper();
    
    public LivroFavoritoDTO update(LivroFavoritoEntity livroFavorito) {
        LivroFavoritoDTO livroFavoritoDTO = new LivroFavoritoDTO();
        livroFavoritoDTO.setId(livroFavorito.getId());
        livroFavoritoDTO.setLivro(livroMapper.update(livroFavorito.getLivro()));
        livroFavoritoDTO.setUsuario(usuarioMapper.update(livroFavorito.getUsuario()));
        return livroFavoritoDTO;
    }

    public LivroFavoritoEntity update(LivroFavoritoDTO livroFavorito) {
        LivroFavoritoEntity livroFavoritoEntity = new LivroFavoritoEntity();
        livroFavoritoEntity.setId(livroFavorito.getId());
        livroFavoritoEntity.setLivro(livroMapper.update(livroFavorito.getLivro()));
        livroFavoritoEntity.setUsuario(usuarioMapper.update(livroFavorito.getUsuario()));
        return livroFavoritoEntity;
    }

    public List<LivroFavoritoEntity> updateListEntity(List<LivroFavoritoDTO> listaDTO){
        return listaDTO.stream()
                .map(this::update)
                .toList();
    }

    public List<LivroFavoritoDTO> updateListDTO(List<LivroFavoritoEntity> listaEntity){
        return listaEntity.stream()
                .map(this::update)
                .toList();
    }
}
