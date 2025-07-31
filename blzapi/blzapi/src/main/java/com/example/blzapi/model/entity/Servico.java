package com.example.blzapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "servicos")
    private List<ProdutoUtilizado> produto;
    @JsonIgnore
    @OneToMany(mappedBy = "servicos")
    private List<OrdemServicos> agendamento;
}
