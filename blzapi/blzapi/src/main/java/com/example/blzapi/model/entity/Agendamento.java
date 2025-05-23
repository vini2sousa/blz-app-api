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

    private long id;

    private String horario;
    private String data;
    private Loja loja;
    private Comanda comanda;
    @ManyToOne
    private Usuario usuario;
    private ClienteLoja cliente;


}
