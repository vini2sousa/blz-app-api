package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.AgendamentoDTO;
import com.example.blzapi.api.dto.ServicoDTO;
import com.example.blzapi.exception.RegraNegocioException;
import com.example.blzapi.model.entity.Agendamento;
import com.example.blzapi.model.entity.Cargo;
import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.entity.Servico;
import com.example.blzapi.model.service.AgendamentoService;
import com.example.blzapi.model.service.CargoService;
import com.example.blzapi.model.service.LojaService;
import com.example.blzapi.model.service.ServicoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/servicos")
@RequiredArgsConstructor
@CrossOrigin
public class ServicoController {

    private final ServicoService service;
    private final LojaService lojaService;
    private final CargoService cargoService;

    @GetMapping()
    public ResponseEntity get() {
        List<Servico> servico = service.getServico();
        return ResponseEntity.ok(servico.stream().map(ServicoDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Servico> aluno = service.getServicoById(id);
        if (!aluno.isPresent()) {
            return new ResponseEntity("Servico não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(aluno.map(ServicoDTO::create));
    }
    @PostMapping()
    public ResponseEntity post(@RequestBody ServicoDTO dto) {
        try {
            Servico servico = converter(dto);
            servico = service.salvar(servico);
            return new ResponseEntity(servico, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody ServicoDTO dto) {
        if (!service.getServicoById(id).isPresent()) {
            return new ResponseEntity("Serviço não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Servico servico = converter(dto);
            servico.setId(id);
            service.salvar(servico);
            return ResponseEntity.ok(servico);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id){
        Optional<Servico> servico = service.getServicoById(id);
        if(!servico.isPresent()){
            return new ResponseEntity("Serviço não encontrado", HttpStatus.NOT_FOUND);
        }try{
            service.excluir(servico.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch(RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

    public Servico converter(ServicoDTO dto) {
        ModelMapper mapper = new ModelMapper();
        Servico servico = mapper.map(dto, Servico.class);

        if(dto.getIdLoja() != null){
            Optional<Loja> loja = lojaService.getLojaById(dto.getIdLoja());
            if(!loja.isPresent()){
                servico.setLoja(null);
            }else {
                servico.setLoja(loja.get());
            }
        }   if(dto.getIdCargo() != null){
            Optional<Cargo> cargo = cargoService.getCargoById(dto.getIdCargo());
            if(!cargo.isPresent()){
                servico.setLoja(null);
            }else {
                servico.setCargo(cargo.get());
            }
        }
        return servico;
    }

}
