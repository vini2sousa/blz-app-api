package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.AgendamentoDTO;
import com.example.blzapi.exception.RegraNegocioException;
import com.example.blzapi.model.entity.Agendamento;
import com.example.blzapi.model.entity.Comanda;
import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.service.AgendamentoService;
import com.example.blzapi.model.service.LojaService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/agendamentos")
@RequiredArgsConstructor
@CrossOrigin

public class AgendamentoController {

    private final AgendamentoService service;
    private final LojaService lojaService;

    @GetMapping()
    public ResponseEntity get() {
        List<Agendamento> alunos = service.getAgendamentos();
        return ResponseEntity.ok(alunos.stream().map(AgendamentoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Agendamento> aluno = service.getAgendamentoById(id);
        if (!aluno.isPresent()) {
            return new ResponseEntity("Agendamento não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(aluno.map(AgendamentoDTO::create));
    }
    @PostMapping()
    public ResponseEntity post(@RequestBody AgendamentoDTO dto) {
        try {
            Agendamento agendamento = converter(dto);
            agendamento = service.salvar(agendamento);
            return new ResponseEntity(agendamento, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody AgendamentoDTO dto) {
        if (!service.getAgendamentoById(id).isPresent()) {
            return new ResponseEntity("Agendamento não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Agendamento agendamento = converter(dto);
            agendamento.setId(id);
            service.salvar(agendamento);
            return ResponseEntity.ok(agendamento);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id){
        Optional<Agendamento> agendamento = service.getAgendamentoById(id);
        if(!agendamento.isPresent()){
            return new ResponseEntity("Agendamento não encontrado", HttpStatus.NOT_FOUND);
        }try{
            service.excluir(agendamento.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch(RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    public Agendamento converter(AgendamentoDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        Agendamento agendamento = modelMapper.map(dto,Agendamento.class);

        if(dto.getIdLoja() != null){

            Optional<Loja> loja = lojaService.getLojaById(dto.getIdLoja());

            if(!loja.isPresent()){
                agendamento.setLoja(null);
            }else{
                agendamento.setLoja(loja.get());
            }
        }

        return agendamento;
    }



}
