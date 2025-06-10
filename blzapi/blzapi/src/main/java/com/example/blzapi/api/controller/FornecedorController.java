package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.FornecerdorDTO;
import com.example.blzapi.model.entity.Fornecedor;
import com.example.blzapi.model.service.FornecedorService;
import com.example.blzapi.model.service.LojaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Fornecedor> aluno = service.getFornecedorById(id);
        if (!aluno.isPresent()) {
            return new ResponseEntity("Fornecedor não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(aluno.map(FornecerdorDTO::create));
    }
    public Fornecedor converter(FornecerdorDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        Fornecedor fornecedor = modelMapper.map(dto,Fornecedor.class);
        return fornecedor;
    }
}
