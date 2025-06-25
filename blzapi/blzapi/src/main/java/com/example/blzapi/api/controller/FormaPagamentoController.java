package com.example.blzapi.api.controller;


import com.example.blzapi.api.dto.ComandaDTO;
import com.example.blzapi.api.dto.FormaPagamentoDTO;
import com.example.blzapi.exception.RegraNegocioException;
import com.example.blzapi.model.entity.Comanda;
import com.example.blzapi.model.entity.FormaPagamento;
import com.example.blzapi.model.service.FormaPagamentoService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/formapagamento")
@RequiredArgsConstructor
@CrossOrigin


public class FormaPagamentoController {

    private final FormaPagamentoService service;

    @GetMapping()
    public ResponseEntity get() {
        List<FormaPagamento> formapagamento = service.getFormaPagamentos();
        return ResponseEntity.ok(formapagamento.stream().map(FormaPagamentoDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<FormaPagamento> aluno = service.getFormaPagamentoById(id);
        if (!aluno.isPresent()) {
            return new ResponseEntity("Forma de pagamento não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(aluno.map(FormaPagamentoDTO::create));
    }
    @PostMapping()
    public ResponseEntity post(@RequestBody FormaPagamentoDTO dto) {
        try {
            FormaPagamento formapagamento = converter(dto);
            formapagamento = service.salvar(formapagamento);
            return new ResponseEntity(formapagamento, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody FormaPagamentoDTO dto) {
        if (!service.getFormaPagamentoById(id).isPresent()) {
            return new ResponseEntity("Forma de Pagamento não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            FormaPagamento formapagamento = converter(dto);
            formapagamento.setId(id);
            service.salvar(formapagamento);
            return ResponseEntity.ok(formapagamento);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id){
        Optional<FormaPagamento> formapagamento = service.getFormaPagamentoById(id);
        if(!formapagamento.isPresent()){
            return new ResponseEntity("Forma de pagamento não encontrado", HttpStatus.NOT_FOUND);
        }try{
            service.excluir(formapagamento.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch(RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    public FormaPagamento converter(FormaPagamentoDTO dto) {

        ModelMapper modelMapper = new ModelMapper();
        FormaPagamento formapagamento = modelMapper.map(dto, FormaPagamento.class);
        return formapagamento;

    }
}