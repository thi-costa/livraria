package com.example.livraria.filter;

import com.example.livraria.model.entity.UsuarioEntity;
import com.example.livraria.service.JwtService;
import com.example.livraria.service.LoginUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomJwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private LoginUserService loginService;

    @Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = request.getHeader("authorization");

		if(token!=null && !token.isBlank() && token.contains("Bearer ")) {
			token = token.trim().replace("Bearer ","");
			if(jwtService.valideToken(token)) {
				String username = jwtService.getUsername(token);
				UsuarioEntity usuario = (UsuarioEntity) loginService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication =
						UsernamePasswordAuthenticationToken.authenticated(usuario.getUsername(), null, usuario.getAuthorities());

				if(SecurityContextHolder.getContext().getAuthentication()==null) {
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}

		filterChain.doFilter(request, response);
	}
}
