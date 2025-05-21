package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.FornecerdorDTO;
import com.example.blzapi.api.dto.LojaDTO;
import com.example.blzapi.model.entity.Fornecedor;
import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.service.LojaService;
import org.modelmapper.ModelMapper;

public class FornecedorController {

    private LojaService service;

    public Fornecedor converter(FornecerdorDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        Fornecedor fornecedor = modelMapper.map(dto,Fornecedor.class);
        return fornecedor;
    }
}
