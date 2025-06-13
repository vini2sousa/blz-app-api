package com.example.blzapi.api.controller;


import com.example.blzapi.api.dto.ColaboradorDTO;
import com.example.blzapi.exception.RegraNegocioException;
import com.example.blzapi.model.entity.Colaborador;
import com.example.blzapi.model.service.ColaboradorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/colaboradores")
@RequiredArgsConstructor
@CrossOrigin
public class ColaboradorController {
    private final ColaboradorService service;


    @GetMapping()
    public ResponseEntity get() {
        List<Colaborador> Colaborador = service.getColaboradors();
        return ResponseEntity.ok(Colaborador.stream().map(ColaboradorDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Colaborador> Colaborador = service.getColaboradorById(id);
        if (!Colaborador.isPresent()) {
            return new ResponseEntity("Colaborador não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(Colaborador.map(ColaboradorDTO::create));
    }
    @PostMapping()
    public ResponseEntity post(@RequestBody ColaboradorDTO dto) {
        try {
            Colaborador Colaborador = converter(dto);
            Colaborador = service.salvar(Colaborador);
            return new ResponseEntity(Colaborador, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    public Colaborador converter(ColaboradorDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        Colaborador Colaborador = modelMapper.map(dto,Colaborador.class);
        return Colaborador;
    }
}
