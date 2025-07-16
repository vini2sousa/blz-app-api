package com.example.blzapi.model.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Comanda{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne
    private FormaPagamento formaPagamento;
    private String horario;
    private String dataComanda;
    @OneToOne
    private Agendamento agendamento;
}