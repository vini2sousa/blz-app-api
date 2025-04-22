package com.example.blzapi.model.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProdutoUtilizado{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private Servico servicos;
    private Produto produtos;
    private int quantidade;
}