package com.example.livraria.service;

import com.example.livraria.model.dto.LivroFavoritoDTO;
import com.example.livraria.model.dto.UsuarioDTO;
import com.example.livraria.model.dto.security.UsuarioComSenhaDTO;
import com.example.livraria.model.entity.*;
import com.example.livraria.model.dto.LivroDTO;
import com.example.livraria.model.mapper.LivroFavoritoMapper;
import com.example.livraria.model.mapper.LivroMapper;
import com.example.livraria.model.mapper.UsuarioMapper;
import com.example.livraria.repository.LivroFavoritoRepository;
import com.example.livraria.repository.LivroRepository;
import com.example.livraria.repository.UsuarioRepository;
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
    @Autowired
    private UsuarioMapper usuarioMapper;
    @Autowired
    private LivroFavoritoMapper mapperFavorito;
    @Autowired
    private LivroFavoritoRepository livroFavoritoRepository;
    @Autowired
    private UsuarioService usuarioService;

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

    public LivroFavoritoDTO favoritar(String username, Long id) {
        if(username.equals("")){
            throw new EntityNotFoundException("Usuário vazio fornecido");
        }

        LivroFavoritoDTO livroFavoritoDTO = new LivroFavoritoDTO();
        LivroDTO livroDTO = pegarPorId(id);

        livroFavoritoDTO.setLivro(livroDTO);
        livroFavoritoDTO.setUsuario(usuarioService.pegarPorUsername(username));

        LivroFavoritoEntity livro = mapperFavorito.update(livroFavoritoDTO);

        livro = livroFavoritoRepository.save(livro);

        return mapperFavorito.update(livro);


    }
    public void desfavoritar(String username, Long id) {
        if(username.equals("")){
            throw new EntityNotFoundException("Usuário vazio fornecido");
        }

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(usuarioService.pegarPorUsername(username).getId());

        LivroEntity livro = new LivroEntity();
        livro.setId(id);

        List<LivroFavoritoEntity> listaLivrosFavoritos =
                livroFavoritoRepository.findByUsuarioAndLivro(usuario, livro);

        // Só deve ter 1 usuário com mesmo livro favoritado (por isso só basta primeiro índice)
        LivroFavoritoEntity livroFavorito = listaLivrosFavoritos.get(0);

        livroFavoritoRepository.delete(livroFavorito);

    }

    public List<LivroDTO> listarFavoritos(String username) {
        if(username.equals("")){
            throw new EntityNotFoundException("Usuário vazio fornecido");
        }

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(usuarioService.pegarPorUsername(username).getId());

        List<LivroFavoritoEntity> listaEntities = livroFavoritoRepository.findByUsuario(usuario);

        List<LivroEntity> listaLivrosFavoritos = listaEntities.stream()
                .map(LivroFavoritoEntity::getLivro).toList();

        if(listaLivrosFavoritos.size() == 0){
            throw new EntityNotFoundException("Usuário não tem livros salvos");
        }

        return mapper.updateListDTO(listaLivrosFavoritos);
    }
}
