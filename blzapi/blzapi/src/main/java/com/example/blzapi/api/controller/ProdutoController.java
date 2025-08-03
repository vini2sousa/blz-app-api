package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.AgendamentoDTO;
import com.example.blzapi.api.dto.ItemVendasDTO;
import com.example.blzapi.api.dto.ProdutoDTO;
import com.example.blzapi.api.dto.ProdutoUtilizadoDTO;
import com.example.blzapi.exception.RegraNegocioException;
import com.example.blzapi.model.entity.*;
import com.example.blzapi.model.service.AgendamentoService;
import com.example.blzapi.model.service.FornecedorService;
import com.example.blzapi.model.service.LojaService;
import com.example.blzapi.model.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Produto> aluno = service.getProdutoById(id);
        if (!aluno.isPresent()) {
            return new ResponseEntity("Produto não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(aluno.map(ProdutoDTO::create));
    }
    @GetMapping("/{id}/itemVendas")
    public ResponseEntity getItemVendas(@PathVariable("id") Long id) {
        Optional<Produto> produto = service.getProdutoById(id);
        if (!produto.isPresent()) {
            return new ResponseEntity("Produto não encontrado", HttpStatus.NOT_FOUND);
        }
        List<ItemVendas> itemVendas = produto.get().getVendas();
        return ResponseEntity.ok(itemVendas.stream().map(ItemVendasDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}/produtoUtilizado")
    public ResponseEntity getProdutoUtilizado(@PathVariable("id") Long id) {
        Optional<Produto> produto = service.getProdutoById(id);
        if (!produto.isPresent()) {
            return new ResponseEntity("Produto não encontrado", HttpStatus.NOT_FOUND);
        }
        List<ProdutoUtilizado> produtoUtilizados = produto.get().getServico();
        return ResponseEntity.ok(produtoUtilizados.stream().map(ProdutoUtilizadoDTO::create).collect(Collectors.toList()));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody ProdutoDTO dto) {
        try {
            Produto produto = converter(dto);
            produto = service.salvar(produto);
            return new ResponseEntity(produto, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody ProdutoDTO dto) {
        if (!service.getProdutoById(id).isPresent()) {
            return new ResponseEntity("Produto não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Produto produto = converter(dto);
            produto.setId(id);
            service.salvar(produto);
            return ResponseEntity.ok(produto);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id){
        Optional<Produto> produto = service.getProdutoById(id);
        if(!produto.isPresent()){
            return new ResponseEntity("Produto não encontrado", HttpStatus.NOT_FOUND);
        }try{
            service.excluir(produto.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch(RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }

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

