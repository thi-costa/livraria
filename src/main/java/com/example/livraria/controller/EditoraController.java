package com.example.livraria.controller;

import com.example.livraria.service.EditoraService;
import com.example.livraria.model.dto.EditoraDTO;
import com.example.livraria.model.dto.MensagemDTO;
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
@RequestMapping("/editoras")
@CrossOrigin(origins = "*")
@Tag(name = "Editoras", description = "Editoras API")
@Slf4j
public class EditoraController {
    @Autowired
    private EditoraService editoraService;
    @GetMapping
    @Operation(summary = "Lista editoras")
    public ResponseEntity<Object> listar(){
        try {
            return ResponseEntity.ok(editoraService.listar());

        }catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }
    @GetMapping("/{id}")
    @Operation(summary = "Busca 1 editora por ID")
    public ResponseEntity<Object> pegarUm(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(editoraService.pegarPorId(id));

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
    @Operation(summary = "Cria 1 editora")
    public ResponseEntity<Object> criar(@RequestBody @Valid EditoraDTO editoraDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(editoraService.criar(editoraDTO));

        }catch(Exception ex) {

            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @Secured("ADMIN")
    @Operation(summary = "Edita 1 editora por ID")
    public ResponseEntity<Object> editar(
            @RequestBody @Valid EditoraDTO produtoDTO,
            @PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(
                    editoraService.editar(produtoDTO, id));

        }catch(EntityNotFoundException ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensagemDTO(ex.getMessage()));

        }catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @Secured("ADMIN")
    @Operation(summary = "Deleta 1 editora por ID")
    public ResponseEntity<Object> deletar(
            @PathVariable("id") Long id) {
        try {
            editoraService.deletar(id);
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
