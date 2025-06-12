package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.ComandaDTO;
import com.example.blzapi.exception.RegraNegocioException;
import com.example.blzapi.model.entity.Agendamento;
import com.example.blzapi.model.entity.Comanda;
import com.example.blzapi.model.entity.FormaPagamento;
import com.example.blzapi.model.service.AgendamentoService;
import com.example.blzapi.model.service.ComandaService;
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
@RequestMapping("/api/v1/comandas")
@RequiredArgsConstructor
@CrossOrigin
public class ComandaController {

    private final ComandaService service;
    private final AgendamentoService agendamentoService;
    private final FormaPagamentoService formaPagamentoService;

    @GetMapping()
    public ResponseEntity get() {
        List<Comanda> comanda = service.getComandas();
        return ResponseEntity.ok(comanda.stream().map(ComandaDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Comanda> aluno = service.getComandaById(id);
        if (!aluno.isPresent()) {
            return new ResponseEntity("Comanda não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(aluno.map(ComandaDTO::create));
    }
    @PostMapping()
    public ResponseEntity post(@RequestBody ComandaDTO dto) {
        try {
            Comanda comanda = converter(dto);
            comanda = service.salvar(comanda);
            return new ResponseEntity(comanda, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Comanda converter(ComandaDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        Comanda comanda = modelMapper.map(dto,Comanda.class);
        if(dto.getIdAgendamento() != null){
            Optional<Agendamento> agendamento = agendamentoService.getAgendamentoById(dto.getIdAgendamento());
            if(!agendamento.isPresent()){

                comanda.setAgendamento(null);

            }else {

                comanda.setAgendamento(agendamento.get());

            }
        }
        if(dto.getIdFormaPagamento() != null){
            Optional<FormaPagamento> formaPagamento = formaPagamentoService.getFormaPagamentoById(dto.getIdFormaPagamento());
            if(!formaPagamento.isPresent()){

                comanda.setTipoPagamento(null);

            }else{

                comanda.setTipoPagamento(formaPagamento.get());

            }
        }
        return comanda;
    }
}
