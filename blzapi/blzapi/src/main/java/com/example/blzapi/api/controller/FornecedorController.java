package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.FornecerdorDTO;
import com.example.blzapi.model.entity.Fornecedor;
import com.example.blzapi.model.service.FornecedorService;
import com.example.blzapi.model.service.LojaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/fornecedores")
@RequiredArgsConstructor
@CrossOrigin
public class FornecedorController {

    private final FornecedorService service;
    private final LojaService lojaService;

    @GetMapping()
    public ResponseEntity get() {
        List<Fornecedor> fornecedor = service.getFornecedor();
        return ResponseEntity.ok(fornecedor.stream().map(FornecerdorDTO::create).collect(Collectors.toList()));
    }
    public Fornecedor converter(FornecerdorDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        Fornecedor fornecedor = modelMapper.map(dto,Fornecedor.class);
        return fornecedor;
    }
}
