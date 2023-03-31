package com.example.livraria.model.mapper;

import com.example.livraria.model.dto.security.UsuarioComSenhaDTO;
import com.example.livraria.model.dto.UsuarioDTO;
import com.example.livraria.model.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    @Autowired
    private PasswordEncoder encoder;
    public UsuarioEntity update(UsuarioComSenhaDTO usuarioDTO){
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setCpf(usuarioDTO.getCpf());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(encoder.encode(usuarioDTO.getPassword()));

        return usuario;
    }
    public UsuarioDTO update(UsuarioEntity usuarioEntity){
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setCpf(usuarioEntity.getCpf());
        usuario.setEmail(usuarioEntity.getEmail());
        usuario.setNome(usuarioEntity.getNome());
        usuario.setUsername(usuarioEntity.getUsername());

        return usuario;
    }
}
