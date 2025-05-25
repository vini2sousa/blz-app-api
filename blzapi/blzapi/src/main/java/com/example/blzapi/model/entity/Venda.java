package com.example.blzapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private FormaPagamento formaPagamento;
    private String data;
    private String horario;
    @ManyToOne
    private Loja loja;
    @ManyToOne
    private Usuario usuario;
}
