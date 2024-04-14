package com.example.FGabriel0.businessModeling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.example.FGabriel0.businessModeling.entity.Usuario;

@Repository
public interface usuarioRepository extends JpaRepository<Usuario, Integer>{
	
    UserDetails findByLogin(String login);

}
