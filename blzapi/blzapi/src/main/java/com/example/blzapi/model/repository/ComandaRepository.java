package com.example.blzapi.model.repository;

import com.example.blzapi.model.entity.Comanda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComandaRepository extends JpaRepository<Comanda,Long> {
}
