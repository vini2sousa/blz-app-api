package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.AgendamentoDTO;
import com.example.blzapi.api.dto.CargoDTO;
import com.example.blzapi.api.dto.FornecerdorDTO;
import com.example.blzapi.model.entity.Agendamento;
import com.example.blzapi.model.entity.Cargo;
import com.example.blzapi.model.entity.Fornecedor;
import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.service.AgendamentoService;
import com.example.blzapi.model.service.CargoService;
import com.example.blzapi.model.service.LojaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/cargos")
@RequiredArgsConstructor
@CrossOrigin

public class CargoController {

    private final CargoService service;
    private final LojaService lojaService;

    @GetMapping()
    public ResponseEntity get() {
        List<Cargo> cargo = service.getCargos();
        return ResponseEntity.ok(cargo.stream().map(CargoDTO::create).collect(Collectors.toList()));
    }

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
