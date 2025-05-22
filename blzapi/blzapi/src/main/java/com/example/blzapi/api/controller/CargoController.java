package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.AgendamentoDTO;
import com.example.blzapi.model.entity.Agendamento;
import com.example.blzapi.model.entity.Cargo;
import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.service.AgendamentoService;
import com.example.blzapi.model.service.CargoService;
import com.example.blzapi.model.service.LojaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/cargos")
@RequiredArgsConstructor
@CrossOrigin

public class CargoController {

    private CargoService service;
    private LojaService lojaService;

    public Cargo converter(AgendamentoDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        Cargo cargo = modelMapper.map(dto,Cargo.class);

        if(dto.getIdLoja() != null){

            Optional<Loja> loja = lojaService.getLojaById(dto.getIdLoja());

            if(!loja.isPresent()){
                cargo.setLoja(null);
            }else{
                cargo.setLoja(loja.get());
            }
        }

        return cargo;
    }

}
