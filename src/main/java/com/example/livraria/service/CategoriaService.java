package com.example.livraria.service;

import com.example.livraria.model.dto.CategoriaDTO;
import com.example.livraria.model.entity.CategoriaEntity;
import com.example.livraria.repository.CategoriaRepository;
import com.example.livraria.model.mapper.CategoriaMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository repository;
    @Autowired
    private CategoriaMapper mapper;

    public CategoriaDTO pegarPorId(Long id){
        Optional<CategoriaEntity> categoriaEntityOp = repository.findById(id);

        if(categoriaEntityOp.isPresent()){
            CategoriaEntity categoriaEntity = categoriaEntityOp.get();
            return mapper.update(categoriaEntity);
        }

        throw new EntityNotFoundException("Categoria não encontrada!");
    }

    public CategoriaDTO criar(CategoriaDTO categoriaDTO){
        CategoriaEntity categoria = mapper.update(categoriaDTO);

        categoria = repository.save(categoria);

        return mapper.update(categoria);
    }

    public CategoriaDTO editar(CategoriaDTO categoriaDTO, Long id){
        if(repository.existsById(id)){
            CategoriaEntity categoriaEntity = mapper.update(categoriaDTO);
            categoriaEntity.setId(id);
            categoriaEntity = repository.save(categoriaEntity);

            return mapper.update(categoriaEntity);
        }

        throw new EntityNotFoundException("Categoria não encontrada!");
    }

    public void deletar(Long id){
        Optional<CategoriaEntity> categoriaEntityOp = repository.findById(id);

        if(categoriaEntityOp.isPresent()){
            CategoriaEntity categoriaEntity = categoriaEntityOp.get();
            repository.delete(categoriaEntity);
            return;
        }

        throw new EntityNotFoundException("Categoria não encontrada!");
    }

    public List<CategoriaDTO> listar(){
        List<CategoriaEntity> listaEntities = repository.findAll();
        return mapper.updateListDTO(listaEntities);
    }
}
