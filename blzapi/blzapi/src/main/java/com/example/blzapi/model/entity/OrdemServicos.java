package com.example.blzapi.model.entity;
import com.example.blzapi.model.entity.Agendamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrdemServicos{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private Integer quantidade;
    @ManyToOne
    private Servico servicos;
    @ManyToOne
    private Agendamento agendamento;

}