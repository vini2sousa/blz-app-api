package com.example.blzapi.api.controller;


import com.example.blzapi.api.dto.ColaboradorDTO;
import com.example.blzapi.api.dto.LojaDTO;
import com.example.blzapi.api.dto.ServicoDTO;
import com.example.blzapi.exception.RegraNegocioException;
import com.example.blzapi.model.entity.Colaborador;
import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.entity.Servico;
import com.example.blzapi.model.service.ColaboradorService;
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
@RequestMapping("/api/v1/colaboradores")
@RequiredArgsConstructor
@CrossOrigin
public class ColaboradorController {

    private final ColaboradorService service;
    private final LojaService lojaService;

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
    @GetMapping("/{id}/lojas")
    public ResponseEntity getLoja(@PathVariable("id") Long id) {
        Optional<Colaborador> Colaborador = service.getColaboradorById(id);
        if (!Colaborador.isPresent()) {
            return new ResponseEntity("Colaborador não encontrado", HttpStatus.NOT_FOUND);
        }
        List<Loja> lojas = Colaborador.get().getLojas();
        return ResponseEntity.ok(lojas.stream().map(LojaDTO::create).collect(Collectors.toList()));
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
    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody ColaboradorDTO dto) {
        if (!service.getColaboradorById(id).isPresent()) {
            return new ResponseEntity("Colaborador não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Colaborador colaborador = converter(dto);
            colaborador.setId(id);
            service.salvar(colaborador);
            return ResponseEntity.ok(colaborador);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id){
        Optional<Colaborador> colaborador = service.getColaboradorById(id);
        if(!colaborador.isPresent()){
            return new ResponseEntity("Colaborador não encontrado", HttpStatus.NOT_FOUND);
        }try{
            service.excluir(colaborador.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch(RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }


    public Colaborador converter(ColaboradorDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Colaborador colaborador = modelMapper.map(dto,Colaborador.class);

        if(dto.getIdLoja() != null){

            Optional<Loja> loja = lojaService.getLojaById(dto.getIdLoja());

            if(!loja.isPresent()){
                colaborador.setLoja(null);
            }else{
                colaborador.setLoja(loja.get());
            }
        }
        return colaborador;
    }
}
