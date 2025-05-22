package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.AgendamentoDTO;
import com.example.blzapi.api.dto.FuncionarioDTO;
import com.example.blzapi.model.entity.Agendamento;
import com.example.blzapi.model.entity.Funcionario;
import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.service.AgendamentoService;
import com.example.blzapi.model.service.FuncionarioService;
import com.example.blzapi.model.service.LojaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/funcionarios")
@RequiredArgsConstructor
@CrossOrigin

public class FuncionarioController {

    private FuncionarioService service;
    private LojaService lojaService;

    public Funcionario converter(FuncionarioDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        Funcionario funcionario = modelMapper.map(dto,Funcionario.class);

        if(dto.getIdLoja() != null){

            Optional<Loja> loja = lojaService.getLojaById(dto.getIdLoja());

            if(!loja.isPresent()){
                funcionario.setLoja(null);
            }else{
                funcionario.setLoja(loja.get());
            }
        }

        return funcionario;
    }
}

