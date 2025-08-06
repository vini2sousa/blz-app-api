package com.example.blzapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ItemVendas{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private Integer quantidade;
    @ManyToOne
    @JoinColumn(name = "id_vendas")
    private Venda vendas;
    @ManyToOne
    private Produto produtos;

}