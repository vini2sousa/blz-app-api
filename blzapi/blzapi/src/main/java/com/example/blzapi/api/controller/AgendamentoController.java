package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.AgendamentoDTO;
import com.example.blzapi.model.entity.Agendamento;
import com.example.blzapi.model.entity.Comanda;
import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.service.AgendamentoService;
import com.example.blzapi.model.service.LojaService;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class AgendamentoController {

    private  AgendamentoService service;
    private  LojaService lojaService;

    public Agendamento converter(AgendamentoDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        Agendamento agendamento = modelMapper.map(dto,Agendamento.class);

        if(dto.getIdLoja() != null){

            Optional<Loja> loja = lojaService.getLojaById(dto.getIdLoja());

            if(!loja.isPresent()){
                agendamento.setLoja(null);
            }else{
                agendamento.setLoja(loja.get());
            }
        }

        return agendamento;
    }
}
