package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.FornecerdorDTO;
import com.example.blzapi.exception.RegraNegocioException;
import com.example.blzapi.model.entity.Agendamento;
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
    @PostMapping()
    public ResponseEntity post(@RequestBody FornecerdorDTO dto) {
        try {
            Fornecedor fornecedor = converter(dto);
            fornecedor = service.salvar(fornecedor);
            return new ResponseEntity(fornecedor, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody FornecerdorDTO dto) {
        if (!service.getFornecedorById(id).isPresent()) {
            return new ResponseEntity("Fornecedor não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Fornecedor fornecedor = converter(dto);
            fornecedor.setId(id);
            service.salvar(fornecedor);
            return ResponseEntity.ok(fornecedor);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id){
        Optional<Fornecedor> fornecedor = service.getFornecedorById(id);
        if(!fornecedor.isPresent()){
            return new ResponseEntity("Fornecedor não encontrado", HttpStatus.NOT_FOUND);
        }try{
            service.excluir(fornecedor.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch(RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
    public Fornecedor converter(FornecerdorDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        Fornecedor fornecedor = modelMapper.map(dto,Fornecedor.class);
        return fornecedor;
    }
}
