package com.example.blzapi.model.repository;

import com.example.blzapi.model.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda,Long> {
}
