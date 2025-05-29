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
@RequestMapping("/api/v1/produtos")
@RequiredArgsConstructor
@CrossOrigin

public class ProdutoController {

    private final ProdutoService service;
    private final LojaService lojaService;
    private final FornecedorService fornecedorService;

    @GetMapping()
    public ResponseEntity get() {
        List<Produto> produto = service.getProdutos();
        return ResponseEntity.ok(produto.stream().map(ProdutoDTO::create).collect(Collectors.toList()));
    }

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

