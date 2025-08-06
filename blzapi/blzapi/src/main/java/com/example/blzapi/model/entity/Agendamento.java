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

public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String horario;
    private String dataAgendamento;
    @ManyToOne
    private Loja loja;
    @OneToOne
    private Comanda comanda;
    @ManyToOne
    private Funcionario funcionario;
    @ManyToOne
    private ClienteLoja cliente;

    @JsonIgnore
    @OneToMany(mappedBy = "agendamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdemServicos> servico;


}
