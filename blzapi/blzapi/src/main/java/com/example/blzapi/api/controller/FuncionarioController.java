package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.AgendamentoDTO;
import com.example.blzapi.api.dto.FuncionarioDTO;
import com.example.blzapi.exception.RegraNegocioException;
import com.example.blzapi.model.entity.Agendamento;
import com.example.blzapi.model.entity.Funcionario;
import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.service.AgendamentoService;
import com.example.blzapi.model.service.FuncionarioService;
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
@RequestMapping("/api/v1/funcionarios")
@RequiredArgsConstructor
@CrossOrigin

public class FuncionarioController {

    private final FuncionarioService service;
    private final LojaService lojaService;

    @GetMapping()
    public ResponseEntity get() {
        List<Funcionario> funcionario = service.getFuncionarios();
        return ResponseEntity.ok(funcionario.stream().map(FuncionarioDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Funcionario> aluno = service.getFuncionarioById(id);
        if (!aluno.isPresent()) {
            return new ResponseEntity("funcionario não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(aluno.map(FuncionarioDTO::create));
    }
    @PostMapping()
    public ResponseEntity post(@RequestBody FuncionarioDTO dto) {
        try {
            Funcionario funcionario = converter(dto);
            funcionario = service.salvar(funcionario);
            return new ResponseEntity(funcionario, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody FuncionarioDTO dto) {
        if (!service.getFuncionarioById(id).isPresent()) {
            return new ResponseEntity("Aluno não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Funcionario funcionario = converter(dto);
            funcionario.setId(id);
            service.salvar(funcionario);
            return ResponseEntity.ok(funcionario);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    public Funcionario converter(FuncionarioDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        Funcionario funcionario = modelMapper.map(dto,Funcionario.class);

        if(dto.getIdLoja() != null){

            Optional<Loja> loja = lojaService.getLojaById(dto.getIdLoja());

            if(!loja.isPresent()){
                funcionario.setLoja(null);
            }else{
                funcionario.setLoja(loja.get());
            }
        }

        return funcionario;
    }
}

