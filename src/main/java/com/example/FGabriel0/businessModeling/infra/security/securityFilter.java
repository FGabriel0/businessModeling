package com.example.FGabriel0.businessModeling.infra.security;

import java.io.IOException;
import java.util.Collection;

import org.h2.command.ddl.CreateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.FGabriel0.businessModeling.repository.clienteRepository;
import com.example.FGabriel0.businessModeling.repository.usuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class securityFilter extends OncePerRequestFilter {
	
	@Autowired
	private tokenService tokenservice;
	
	@Autowired
	private usuarioRepository repository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recoverToken(request);
		if(token != null) {
			
			String login = tokenservice.validateToken(token);
			
			UserDetails user = repository.findByLogin(login);
					
            var authentication = new UsernamePasswordAuthenticationToken(user,
            			null, user.getAuthorities());
            
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		}
		filterChain.doFilter(request, response);
	}

	private String recoverToken(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		
		if(authHeader == null) {
			return null;
		}
		
		return authHeader.replace("Bearer ", "");
	}

}
