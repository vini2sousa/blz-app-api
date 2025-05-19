package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.Agendamento;
import com.example.blzapi.model.entity.Comanda;
import com.example.blzapi.model.entity.FormaPagamento;
import org.modelmapper.ModelMapper;


public class ComandaDTO {
    private long id;
    private FormaPagamento tipoPagamento;
    private String horario;
    private Agendamento agendamento;

    public static ComandaDTO create(Comanda comanda){

        ModelMapper modelMapper = new ModelMapper();
        ComandaDTO dto = modelMapper.map(comanda, ComandaDTO.class);
        return dto;
    }
}
