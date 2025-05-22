package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.ComandaDTO;
import com.example.blzapi.model.entity.Agendamento;
import com.example.blzapi.model.entity.Comanda;
import com.example.blzapi.model.entity.FormaPagamento;
import com.example.blzapi.model.service.AgendamentoService;
import com.example.blzapi.model.service.ComandaService;
import com.example.blzapi.model.service.FormaPagamentoService;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class ComandaController {

    private ComandaService service;
    private AgendamentoService agendamentoService;
    private FormaPagamentoService formaPagamentoService;

    public Comanda converter(ComandaDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        Comanda comanda = modelMapper.map(dto,Comanda.class);
        if(dto.getIdAgendamento() != null){
            Optional<Agendamento> agendamento = agendamentoService.getAgendamentoById(dto.getIdAgendamento());
            if(!agendamento.isPresent()){

                comanda.setAgendamento(null);

            }else {

                comanda.setAgendamento(agendamento.get());

            }
        }
        if(dto.getIdFormaPagamento() != null){
            Optional<FormaPagamento> formaPagamento = formaPagamentoService.getFormaPagamentoById(dto.getIdFormaPagamento());
            if(!formaPagamento.isPresent()){

                comanda.setTipoPagamento(null);

            }else{

                comanda.setTipoPagamento(formaPagamento.get());

            }
        }
        return comanda;
    }
}
