package com.example.blzapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int duracao;
    private int preco;
    private float comissao;
    private float desconto;
    @ManyToOne
    private Loja loja;
    @ManyToOne
    private Cargo cargo;
}
