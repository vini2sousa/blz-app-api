package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.VendaDTO;
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
@RequestMapping("/api/v1/vendas")
@RequiredArgsConstructor
@CrossOrigin
public class VendaController {

    private final VendaService service;
    private final LojaService lojaService;
    private final UsuarioService usuarioService;
    private final FormaPagamentoService formaPagamentoService;

    @GetMapping()
    public ResponseEntity get() {
        List<Venda> fornecedor = service.getVendas();
        return ResponseEntity.ok(fornecedor.stream().map(VendaDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Venda> aluno = service.getVendaById(id);
        if (!aluno.isPresent()) {
            return new ResponseEntity("Aluno não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(aluno.map(VendaDTO::create));
    }
    @PostMapping()
    public ResponseEntity post(@RequestBody VendaDTO dto) {
        try {
            Venda venda = converter(dto);
            venda = service.salvar(venda);
            return new ResponseEntity(venda, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody VendaDTO dto) {
        if (!service.getVendaById(id).isPresent()) {
            return new ResponseEntity("Aluno não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Venda venda = converter(dto);
            venda.setId(id);
            service.salvar(venda);
            return ResponseEntity.ok(venda);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    public Venda converter(VendaDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        Venda venda = modelMapper.map(dto,Venda.class);

        if(dto.getIdLoja() != null){

            Optional<Loja> loja = lojaService.getLojaById(dto.getIdLoja());

            if(!loja.isPresent()){
                venda.setLoja(null);
            }else{
                venda.setLoja(loja.get());
            }
        }
        if(dto.getIdUsuario() != null){

            Optional<Usuario> usuario = usuarioService.getUsuarioById(dto.getIdUsuario());

            if(!usuario.isPresent()){
                venda.setUsuario(null);
            }else {
                venda.setUsuario(usuario.get());
            }
        }
        if(dto.getIdFormaPagamento() != null){
            Optional<FormaPagamento> formaPagamento = formaPagamentoService.getFormaPagamentoById(dto.getIdFormaPagamento());
            if(!formaPagamento.isPresent()){
                venda.setFormaPagamento(null);
            }
            else {
                venda.setFormaPagamento(formaPagamento.get());
            }
        }

        return venda;
    }
}
