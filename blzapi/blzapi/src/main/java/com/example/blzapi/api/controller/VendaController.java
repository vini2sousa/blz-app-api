package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.VendaDTO;
import com.example.blzapi.model.entity.*;
import com.example.blzapi.model.service.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
