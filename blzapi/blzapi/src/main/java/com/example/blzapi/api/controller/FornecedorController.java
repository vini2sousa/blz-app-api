package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.FornecerdorDTO;
import com.example.blzapi.api.dto.LojaDTO;
import com.example.blzapi.model.entity.Fornecedor;
import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.service.LojaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fornecedores")
@RequiredArgsConstructor
@CrossOrigin
public class FornecedorController {

    private LojaService service;

    public Fornecedor converter(FornecerdorDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        Fornecedor fornecedor = modelMapper.map(dto,Fornecedor.class);
        return fornecedor;
    }
}
