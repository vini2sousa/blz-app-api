package com.example.blzapi.model.entity;

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

public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "formaPagamento")
    private List<Venda> vendas;
    @JsonIgnore
    @OneToMany(mappedBy = "formaPagamento")
    private List<Comanda> comanda;
}
