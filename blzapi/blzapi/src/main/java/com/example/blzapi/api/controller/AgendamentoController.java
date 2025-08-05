package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.AgendamentoDTO;
import com.example.blzapi.api.dto.OrdemServicosDTO;
import com.example.blzapi.exception.RegraNegocioException;
import com.example.blzapi.model.entity.*;
import com.example.blzapi.model.service.*;
import lombok.RequiredArgsConstructor;
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
    private final ClienteLojaService clienteLojaService; // Nome do serviço de cliente correto
    private final FuncionarioService funcionarioService;
    private final ServicoService servicoService;


    @GetMapping()
    public ResponseEntity get() {
        List<Agendamento> agendamentos = service.getAgendamentos();
        return ResponseEntity.ok(agendamentos.stream().map(AgendamentoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Agendamento> agendamento = service.getAgendamentoById(id);
        if (!agendamento.isPresent()) {
            return new ResponseEntity("Agendamento não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(agendamento.map(AgendamentoDTO::create));
    }

    @GetMapping("/{id}/ordemServicos")
    public ResponseEntity getOrdemServico(@PathVariable("id") Long id) {
        Optional<Agendamento> agendamento = service.getAgendamentoById(id);
        if (!agendamento.isPresent()) {
            return new ResponseEntity("Agendamento não encontrado", HttpStatus.NOT_FOUND);
        }
        List<OrdemServicos> ordemServico = agendamento.get().getServico();
        return ResponseEntity.ok(ordemServico.stream().map(ordem -> (OrdemServicosDTO) OrdemServicosDTO.create(ordem)).collect(Collectors.toList()));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody AgendamentoDTO dto) {
        try {
            Agendamento agendamento = converter(dto);
            agendamento = service.salvar(agendamento);
            return new ResponseEntity(AgendamentoDTO.create(agendamento), HttpStatus.CREATED);
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
            agendamento = service.salvar(agendamento);
            return ResponseEntity.ok(AgendamentoDTO.create(agendamento));
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id){
        Optional<Agendamento> agendamento = service.getAgendamentoById(id);
        if(!agendamento.isPresent()){
            return new ResponseEntity("Agendamento não encontrado", HttpStatus.NOT_FOUND);
        }
        try{
            service.excluir(agendamento.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch(RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Agendamento converter(AgendamentoDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        Agendamento agendamento = modelMapper.map(dto,Agendamento.class);

        // Lógica para Loja
        if(dto.getIdLoja() != null){
            lojaService.getLojaById(dto.getIdLoja())
                    .ifPresentOrElse(agendamento::setLoja,
                            () -> { throw new RegraNegocioException("Loja não encontrada para o ID: " + dto.getIdLoja()); });
        }

        // LÓGICA CORRIGIDA: Usando clienteService para buscar e setar um Cliente
        if(dto.getIdCliente() != null){
            clienteLojaService.getClienteLojaById(dto.getIdCliente())
                    .ifPresentOrElse(agendamento::setCliente,
                            () -> { throw new RegraNegocioException("Cliente não encontrado para o ID: " + dto.getIdCliente()); });
        }

        // Lógica para Funcionário
        if(dto.getIdFuncionario() != null){
            funcionarioService.getFuncionarioById(dto.getIdFuncionario())
                    .ifPresentOrElse(agendamento::setFuncionario,
                            () -> { throw new RegraNegocioException("Funcionário não encontrado para o ID: " + dto.getIdFuncionario()); });
        }

        // Lógica para processar a lista de serviços
        if (dto.getServicos() != null && !dto.getServicos().isEmpty()) {
            List<OrdemServicos> ordensDeServico = dto.getServicos().stream().map(servicoDto -> {
                Servico servico = servicoService.getServicoById(servicoDto.getIdServico())
                        .orElseThrow(() -> new RegraNegocioException("Serviço não encontrado para o ID: " + servicoDto.getIdServico()));

                OrdemServicos ordem = new OrdemServicos();
                ordem.setAgendamento(agendamento);
                ordem.setServicos(servico);
                ordem.setQuantidade(servicoDto.getQuantidade());
                return ordem;
            }).collect(Collectors.toList());
            agendamento.setServico(ordensDeServico);
        }

        return agendamento;
    }
}