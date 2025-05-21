package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.LojaDTO;
import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.service.LojaService;
import org.modelmapper.ModelMapper;

public class LojaController {

    private  LojaService service;

    public Loja converter(LojaDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        Loja loja = modelMapper.map(dto,Loja.class);
        return loja;
    }
}
