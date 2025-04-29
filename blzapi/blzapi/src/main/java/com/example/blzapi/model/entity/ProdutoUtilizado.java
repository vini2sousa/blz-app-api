package com.example.blzapi.model.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProdutoUtilizado{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    @ManyToOne
    private Servico servicos;
    @ManyToOne
    private Produto produtos;
    private int quantidade;
}