package com.example.livraria.controller;

import com.example.livraria.model.dto.MensagemDTO;
import com.example.livraria.model.dto.security.UsuarioComSenhaDTO;
import com.example.livraria.model.dto.security.UsuarioLoginDTO;
import com.example.livraria.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
@Tag(name = "Usuarios", description = "Usuarios API")
@Slf4j
public class UsuarioController {
    @Autowired
    private UsuarioService service;
    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody UsuarioComSenhaDTO usuario){
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.criar(usuario));

        } catch (Exception ex){
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<Object> entrar(@RequestBody UsuarioLoginDTO usuario){
        try {
            service.entrar(usuario);

            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException ex){
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new MensagemDTO(ex.getMessage()));

        } catch (Exception ex){
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

}
