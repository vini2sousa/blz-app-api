package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.LojaDTO;
import com.example.blzapi.model.entity.Loja;
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
@RequestMapping("/api/v1/lojas")
@RequiredArgsConstructor
@CrossOrigin

public class LojaController {

    private final LojaService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Loja> loja = service.getLojas();
        return ResponseEntity.ok(loja.stream().map(LojaDTO::create).collect(Collectors.toList()));
    }
    public Loja converter(LojaDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        Loja loja = modelMapper.map(dto,Loja.class);
        return loja;
    }
}
