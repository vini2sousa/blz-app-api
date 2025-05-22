package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.AgendamentoDTO;
import com.example.blzapi.api.dto.VendaDTO;
import com.example.blzapi.model.entity.*;
import com.example.blzapi.model.service.*;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class VendaController {

    private VendaService service;
    private LojaService lojaService;
    private UsuarioService usuarioService;
    private FormaPagamentoService formaPagamentoService;

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
