package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.LojaDTO;
import com.example.blzapi.exception.RegraNegocioException;
import com.example.blzapi.model.entity.Agendamento;
import com.example.blzapi.model.entity.Loja;
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
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Loja> aluno = service.getLojaById(id);
        if (!aluno.isPresent()) {
            return new ResponseEntity("Aluno não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(aluno.map(LojaDTO::create));
    }
    @PostMapping()
    public ResponseEntity post(@RequestBody LojaDTO dto) {
        try {
            Loja loja = converter(dto);
            loja = service.salvar(loja);
            return new ResponseEntity(loja, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody LojaDTO dto) {
        if (!service.getLojaById(id).isPresent()) {
            return new ResponseEntity("Aluno não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Loja loja = converter(dto);
            loja.setId(id);
            service.salvar(loja);
            return ResponseEntity.ok(loja);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id){
        Optional<Loja> loja = service.getLojaById(id);
        if(!loja.isPresent()){
            return new ResponseEntity("Aluno não encontrado", HttpStatus.NOT_FOUND);
        }try{
            service.excluir(loja.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch(RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
    public Loja converter(LojaDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        Loja loja = modelMapper.map(dto,Loja.class);
        return loja;
    }
}
