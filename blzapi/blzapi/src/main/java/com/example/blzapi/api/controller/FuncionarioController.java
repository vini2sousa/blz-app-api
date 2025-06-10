package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.AgendamentoDTO;
import com.example.blzapi.api.dto.FuncionarioDTO;
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

