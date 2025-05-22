package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.AgendamentoDTO;
import com.example.blzapi.model.entity.Agendamento;
import com.example.blzapi.model.entity.Comanda;
import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.service.AgendamentoService;
import com.example.blzapi.model.service.LojaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/agendamentos")
@RequiredArgsConstructor
@CrossOrigin

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
