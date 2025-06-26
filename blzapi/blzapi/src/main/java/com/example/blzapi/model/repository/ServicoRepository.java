package com.example.blzapi.model.repository;

import com.example.blzapi.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico,Long> {
}
