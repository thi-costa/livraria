package com.example.livraria.service;

import com.example.livraria.model.entity.LivroEntity;
import com.example.livraria.model.dto.LivroDTO;
import com.example.livraria.model.entity.CategoriaEntity;
import com.example.livraria.model.entity.EditoraEntity;
import com.example.livraria.model.mapper.LivroMapper;
import com.example.livraria.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository repository;
    @Autowired
    private LivroMapper mapper;

    public LivroDTO pegarPorId(Long id){
        Optional<LivroEntity> livroEntityOp =
                repository.findById(id);

        if(livroEntityOp.isPresent()) {
            LivroEntity livroEntity = livroEntityOp.get();
            return mapper.update(livroEntity);
        }

        throw new EntityNotFoundException("Livro não encontrado!");
    }
    public LivroDTO criar(LivroDTO livroDTO) {

        LivroEntity livro = mapper.update(livroDTO);

        livro = repository.save(livro);

        return mapper.update(livro);
    }
    public LivroDTO editar(LivroDTO livroDTO, Long id) {

        if(repository.existsById(id)) {

            LivroEntity livroEntity = mapper.update(livroDTO);
            livroEntity.setId(id);
            livroEntity = repository.save(livroEntity);

            return mapper.update(livroEntity);
        }

        throw new EntityNotFoundException("Livro não encontrada!");
    }
    public void deletar(Long id){
        Optional<LivroEntity> livroEntityOp =
                repository.findById(id);

        if(livroEntityOp.isPresent()) {
            LivroEntity livroEntity = livroEntityOp.get();
            repository.delete(livroEntity);
            return;
        }

        throw new EntityNotFoundException("Livro não encontrada!");
    }
    public List<LivroDTO> listar() {
        List<LivroEntity> listaEntities =  repository.findAll();
        return mapper.updateListDTO(listaEntities);
    }
    public List<LivroDTO> listarPorCategoria(Long id) {
        CategoriaEntity categoria = new CategoriaEntity();
        categoria.setId(id);
        List<LivroEntity> listaEntities =  repository.findByCategoria(categoria);
        return mapper.updateListDTO(listaEntities);
    }
    public List<LivroDTO> listarPorEditora(Long id) {
        EditoraEntity editora = new EditoraEntity();
        editora.setId(id);
        List<LivroEntity> listaEntities =  repository.findByEditora(editora);
        return mapper.updateListDTO(listaEntities);
    }
    public List<LivroDTO> filtrarPorNomeOuIsbn(String nome, String isbn){
        List<LivroEntity> listaEntities = repository.findByNomeOrIsbn(nome, isbn);

        return mapper.updateListDTO(listaEntities);
    }
}
