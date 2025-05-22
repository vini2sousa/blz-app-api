package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.AgendamentoDTO;
import com.example.blzapi.api.dto.ProdutoDTO;
import com.example.blzapi.model.entity.Agendamento;
import com.example.blzapi.model.entity.Fornecedor;
import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.entity.Produto;
import com.example.blzapi.model.service.AgendamentoService;
import com.example.blzapi.model.service.FornecedorService;
import com.example.blzapi.model.service.LojaService;
import com.example.blzapi.model.service.ProdutoService;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class ProdutoController {

    private ProdutoService service;
    private LojaService lojaService;
    private FornecedorService fornecedorService;

    public Produto converter(ProdutoDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        Produto produto = modelMapper.map(dto,Produto.class);

        if(dto.getIdLoja() != null){

            Optional<Loja> loja = lojaService.getLojaById(dto.getIdLoja());

            if(!loja.isPresent()){
                produto.setLoja(null);
            }else{
                produto.setLoja(loja.get());
            }
        }
        if(dto.getIdFornecedor() != null){

            Optional<Fornecedor> fornecedor = fornecedorService.getFornecedorById(dto.getIdFornecedor());

            if(!fornecedor.isPresent()){
                produto.setFornecedor(null);
            }else{
                produto.setFornecedor(fornecedor.get());
            }
        }

        return produto;
    }
}

