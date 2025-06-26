package com.example.blzapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String horario;
    private String data;
    @ManyToOne
    private Loja loja;
    @OneToOne
    private Comanda comanda;
    @ManyToOne
    private ClienteLoja cliente;
    @ManyToOne
    private Funcionario funcionario;

}
