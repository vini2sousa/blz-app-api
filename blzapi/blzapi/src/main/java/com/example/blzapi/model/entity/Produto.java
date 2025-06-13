package com.example.blzapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private float valorCompra;
    private float valorVenda;
    private int unidadeMedida;
    private float desconto;
    private int quantidade;
    private String dataValidade;
    private int quantidadeMin;
    @ManyToOne
    private Loja loja;
    @ManyToOne
    private Usuario fornecedor;


}
