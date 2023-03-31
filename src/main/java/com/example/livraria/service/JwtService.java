package com.example.livraria.service;

import com.example.livraria.model.dto.UsuarioDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public String gerarToken(UsuarioDTO usuario){
        return Jwts
                .builder()
                .setSubject(usuario.getUsername())
                .setIssuer(usuario.getNome())
                .setIssuedAt(new Date())
                .signWith(secretKey)
                .compact();
    }
}
