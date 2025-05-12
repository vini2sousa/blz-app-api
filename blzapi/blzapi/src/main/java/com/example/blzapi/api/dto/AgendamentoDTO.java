package com.example.blzapi.api.dto;


import com.example.blzapi.model.entity.Agendamento;
import org.modelmapper.ModelMapper;

public class AgendamentoDTO {

    private long id;
    private String horario;
    private String data;
    private long idLoja;
    private String nomeLoja;
    private long idComanda;
    private long idUsuario;
    private String nomeUsuario;
    private long idCliente;
    private String nomeCliente;

    public static AgendamentoDTO create(Agendamento agendamento){

        ModelMapper modelMapper = new ModelMapper();
        AgendamentoDTO dto = modelMapper.map(agendamento, AgendamentoDTO.class);
        return dto;
    }
}
