package com.example.blzapi.model.repository;

import com.example.blzapi.model.entity.ClienteLoja;
import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteLojaRepository extends JpaRepository<ClienteLoja,Long> {

}
