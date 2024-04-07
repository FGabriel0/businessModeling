package com.example.FGabriel0.businessModeling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.FGabriel0.businessModeling.repository.clienteRepository;

@Service
public class AuthService implements UserDetailsService{

	@Autowired
	clienteRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByNome(username);
	}
	
}
