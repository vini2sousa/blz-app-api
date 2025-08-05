package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.ClienteLojaDTO;
import com.example.blzapi.api.dto.ItemVendasDTO;
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
    private final ProdutoService produtoService;
    @GetMapping()
    public ResponseEntity get() {
        List<Venda> fornecedor = service.getVendas();
        return ResponseEntity.ok(fornecedor.stream().map(VendaDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Venda> aluno = service.getVendaById(id);
        if (!aluno.isPresent()) {
            return new ResponseEntity("Venda não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(aluno.map(VendaDTO::create));
    }
    @GetMapping("/{id}/itemVendas")
    public ResponseEntity getItemVendas(@PathVariable("id") Long id) {
        Optional<Venda> venda = service.getVendaById(id);
        if (!venda.isPresent()) {
            return new ResponseEntity("Vendas não encontrado", HttpStatus.NOT_FOUND);
        }
        List<ItemVendas> itemVendas = venda.get().getItemVendas();
        return ResponseEntity.ok(itemVendas.stream().map(ItemVendasDTO::create).collect(Collectors.toList()));
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
            return new ResponseEntity("Venda não encontrado", HttpStatus.NOT_FOUND);
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
    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id){
        Optional<Venda> venda = service.getVendaById(id);
        if(!venda.isPresent()){
            return new ResponseEntity("Venda não encontrado", HttpStatus.NOT_FOUND);
        }try{
            service.excluir(venda.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch(RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }


    public Venda converter(VendaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Venda venda = modelMapper.map(dto, Venda.class);

        // Lógica para Loja, Usuário e FormaPagamento (mantida e otimizada)
        if (dto.getIdLoja() != null) {
            lojaService.getLojaById(dto.getIdLoja()).ifPresent(venda::setLoja);
        }
        if (dto.getIdUsuario() != null) {
            usuarioService.getUsuarioById(dto.getIdUsuario()).ifPresent(venda::setUsuario);
        }
        if (dto.getIdFormaPagamento() != null) {
            formaPagamentoService.getFormaPagamentoById(dto.getIdFormaPagamento()).ifPresent(venda::setFormaPagamento);
        }

        // NOVO: Lógica para processar os itens da venda
        if (dto.getItens() != null && !dto.getItens().isEmpty()) {
            List<ItemVendas> itensDaVenda = dto.getItens().stream().map(itemDto -> {
                Produto produto = produtoService.getProdutoById(itemDto.getIdProduto())
                        .orElseThrow(() -> new RegraNegocioException("Produto não encontrado para o ID: " + itemDto.getIdProduto()));

                ItemVendas itemVenda = new ItemVendas();
                itemVenda.setVendas(venda); // Associa o item à venda que estamos criando
                itemVenda.setProdutos(produto); // Associa o item ao produto encontrado
                itemVenda.setQuantidade(itemDto.getQuantidade()); // Define a quantidade
                return itemVenda;
            }).collect(Collectors.toList());

            venda.setItemVendas(itensDaVenda);
        }
        return venda;
    }
}
