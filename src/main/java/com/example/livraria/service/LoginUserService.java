package com.example.livraria.service;

import com.example.livraria.model.entity.UsuarioEntity;
import com.example.livraria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUserService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioEntity> usuarioOp = repository.findByUsername(username);

        if(usuarioOp.isPresent()){
            return usuarioOp.get();
        }

        throw new UsernameNotFoundException("Usuário não encontrado");
    }
}
