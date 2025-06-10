package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.AgendamentoDTO;
import com.example.blzapi.model.entity.Agendamento;
import com.example.blzapi.model.entity.Comanda;
import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.service.AgendamentoService;
import com.example.blzapi.model.service.LojaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/agendamentos")
@RequiredArgsConstructor
@CrossOrigin

public class AgendamentoController {

    private final AgendamentoService service;
    private final LojaService lojaService;

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Agendamento> aluno = service.getAgendamentoById(id);
        if (!aluno.isPresent()) {
            return new ResponseEntity("Agendamento não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(aluno.map(AgendamentoDTO::create));
    }

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
