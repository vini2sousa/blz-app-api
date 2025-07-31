package com.example.blzapi.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    private Fornecedor fornecedor;

    @JsonIgnore
    @OneToMany(mappedBy = "produtos")
    private List<ProdutoUtilizado> servico;
    @JsonIgnore
    @OneToMany(mappedBy = "produtos")
    private List<ItemVendas> vendas;


}
