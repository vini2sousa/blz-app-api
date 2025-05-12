package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.Comanda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ComandaDTO {

    private long id;
    private long idTipoPagamento;
    private String nomeTipoPagamento;
    private String horario;
    private long idAgendamento;
    private String nomeAgendamento;

    public static ComandaDTO create(Comanda comanda){

        ModelMapper modelMapper =  new ModelMapper();
        ComandaDTO dto = modelMapper.map(comanda, ComandaDTO.class);
        return dto;

    }
}
