package com.example.livraria.controller;

import com.example.livraria.model.dto.MensagemDTO;
import com.example.livraria.service.LivroService;
import com.example.livraria.model.dto.LivroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livros")
@CrossOrigin(origins = "*")
@Tag(name = "Livros", description = "Livros API")
@Slf4j
public class LivroController {
    @Autowired
    private LivroService livroService;
    @GetMapping
    @Operation(summary = "Lista todos os livros")
    public ResponseEntity<Object> listar(){
        try {
            return ResponseEntity.ok(livroService.listar());

        } catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca 1 livro por ID")
    public ResponseEntity<Object> pegarUm(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(livroService.pegarPorId(id));

        } catch(EntityNotFoundException ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new MensagemDTO(ex.getMessage()));

        } catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @PostMapping
    @Operation(summary = "Cria 1 livro")
    public ResponseEntity<Object> criar(@RequestBody @Valid LivroDTO livroDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(livroService.criar(livroDTO));

        } catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edita 1 livro por ID")
    public ResponseEntity<Object> editar(
            @RequestBody @Valid LivroDTO livroDTO,
            @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(
                    livroService.editar(livroDTO, id));

        } catch(EntityNotFoundException ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensagemDTO(ex.getMessage()));

        } catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta 1 livro por ID")
    public ResponseEntity<Object> deletar(
            @PathVariable("id") Long id) {
        try {
            livroService.deletar(id);
            return ResponseEntity
                    .ok(new MensagemDTO("Livro com id "+id+" removido com sucesso!"));

        } catch(EntityNotFoundException ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensagemDTO(ex.getMessage()));

        } catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }
    @GetMapping("/categoria/{idCategoria}")
    @Operation(summary = "Busca livros pela categoria")
    public ResponseEntity<Object> pegarPorCategoria(@PathVariable Long idCategoria){
        try {
            return ResponseEntity.ok(livroService.listarPorCategoria(idCategoria));

        } catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }

    }
    @GetMapping("/editora/{idEditora}")
    @Operation(summary = "Busca livros por editora")
    public ResponseEntity<Object> pegarPorEditora(@PathVariable Long idEditora){
        try {
            return ResponseEntity.ok(livroService.listarPorEditora(idEditora));

        } catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }
    @GetMapping("/filter")
    @Operation(summary = "Filtra livro por 'nome' ou ISBN")
    public ResponseEntity<Object> pegarPorNomeOuIsbn(
            @RequestParam(name="nome",defaultValue = "#") String nome,
            @RequestParam(name="isbn",defaultValue = "0") String isbn){
        try {
            return ResponseEntity.ok(livroService.filtrarPorNomeOuIsbn(nome, isbn));

        }catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }

    }

}
