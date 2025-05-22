package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.LojaDTO;
import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.service.LojaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/lojas")
@RequiredArgsConstructor
@CrossOrigin

public class LojaController {

    private  LojaService service;

    public Loja converter(LojaDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        Loja loja = modelMapper.map(dto,Loja.class);
        return loja;
    }
}
