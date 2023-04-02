package com.example.livraria.service;

import com.example.livraria.model.dto.LivroDTO;
import com.example.livraria.model.dto.security.TokenDTO;
import com.example.livraria.model.dto.security.UsuarioComSenhaDTO;
import com.example.livraria.model.dto.UsuarioDTO;
import com.example.livraria.model.dto.security.UsuarioLoginDTO;
import com.example.livraria.model.entity.UsuarioEntity;
import com.example.livraria.model.mapper.UsuarioMapper;
import com.example.livraria.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioMapper mapper;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtService jwtService;

    public UsuarioDTO criar(UsuarioComSenhaDTO usuarioDTO){
        UsuarioEntity usuario = mapper.update(usuarioDTO);
        usuario = repository.save(usuario);
        return mapper.update(usuario);
    }

    public TokenDTO entrar(UsuarioLoginDTO usuario) {
        UsernamePasswordAuthenticationToken authentication = UsernamePasswordAuthenticationToken
                .authenticated(usuario.getUsername(), usuario.getPassword(), null);
        Authentication auth = authManager.authenticate(authentication);

        if(auth.isAuthenticated()){
            UsuarioEntity usuarioEntity = (UsuarioEntity) auth.getPrincipal();

            String token = jwtService.gerarToken(mapper.update(usuarioEntity));

            return new TokenDTO("Bearer", token);

        }

        throw new AuthenticationCredentialsNotFoundException("Usuário inexistente ou senha inválida");
    }

    public UsuarioDTO pegarPorUsername(String username){
        Optional<UsuarioEntity> usuario = repository.findByUsername(username);
        if(usuario.isPresent()){
            UsuarioEntity usuarioEntity = usuario.get();
            UsuarioDTO usuarioDTO = mapper.update(usuarioEntity);
            usuarioDTO.setId(usuarioEntity.getId());
            return usuarioDTO;
        }

        throw new EntityNotFoundException("Usuário inexistente");
    }

}
