package com.example.livraria.controller;

import com.example.livraria.model.dto.CategoriaDTO;
import com.example.livraria.model.dto.MensagemDTO;
import com.example.livraria.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
@Tag(name = "Categorias", description = "Categorias API")
@Slf4j
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;
    @GetMapping
    @Operation(summary = "Lista categorias")
    public ResponseEntity<Object> listar(){

        try {
            return ResponseEntity.ok(categoriaService.listar());

        } catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }
    @GetMapping("/{id}")
    @Operation(summary = "Busca 1 categoria por ID")
    public ResponseEntity<Object> pegarUm(@PathVariable("id") Long id){
        try {

            return ResponseEntity.ok(categoriaService.pegarPorId(id));

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
    @Secured("ADMIN")
    @Operation(summary = "Cria 1 categoria")
    public ResponseEntity<Object> criar(@RequestBody @Valid CategoriaDTO categoriaDTO) {
        try {

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(categoriaService.criar(categoriaDTO));

        } catch(Exception ex) {

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }
    @PutMapping("/{id}")
    @Secured("ADMIN")
    @Operation(summary = "Edita 1 categoria por ID")
    public ResponseEntity<Object> editar(
            @RequestBody @Valid CategoriaDTO categoriaDTO,
            @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(
                    categoriaService.editar(categoriaDTO, id));

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
    @Secured("ADMIN")
    @Operation(summary = "Deleta 1 categoria por ID")
    public ResponseEntity<Object> deletar(
            @PathVariable("id") Long id) {
        try {
            categoriaService.deletar(id);
            return ResponseEntity
                    .ok(new MensagemDTO("Categoria com id "+id+" removido com sucesso!"));

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

}
