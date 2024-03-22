package com.example.FGabriel0.businessModeling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.FGabriel0.businessModeling.entity.Produto;

public interface produtoRepository extends JpaRepository<Produto, Integer> {
	
	@Query(value = "SELECT * FROM Produto c WHERE c.descricao LIKE %:descricao%", nativeQuery = true)
	List<Produto> encontrarPorNome(@Param("descricao") String descricao);

    @Query(" delete from Produto c where c.descricao =:descricao ")
    @Modifying
    void deleteByDescricao(String descricao);

    boolean existsByDescricao(String descricao);
    
}
