package com.example.blzapi.model.repository;

import com.example.blzapi.model.entity.ProdutoUtilizado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoUtilizadoRepository extends JpaRepository<ProdutoUtilizado,Long> {
}
