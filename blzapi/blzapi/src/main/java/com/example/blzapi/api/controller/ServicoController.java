package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.AgendamentoDTO;
import com.example.blzapi.api.dto.ServicoDTO;
import com.example.blzapi.model.entity.Agendamento;
import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.entity.Servico;
import com.example.blzapi.model.service.AgendamentoService;
import com.example.blzapi.model.service.LojaService;
import com.example.blzapi.model.service.ServicoService;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class ServicoController {

    private ServicoService service;
    private LojaService lojaService;

    public Servico converter(ServicoDTO dto) {
        ModelMapper mapper = new ModelMapper();
        Servico servico = mapper.map(dto, Servico.class);

        if(dto.getIdLoja() != null){
            Optional<Loja> loja = lojaService.getLojaById(dto.getIdLoja());
            if(!loja.isPresent()){
                servico.setLoja(null);
            }else {
                servico.setLoja(loja.get());
            }
        }
        return servico;
    }

}
