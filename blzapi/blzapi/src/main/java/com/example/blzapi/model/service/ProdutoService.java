package com.example.blzapi.model.service;

import com.example.blzapi.model.entity.Produto;
import com.example.blzapi.model.repository.ProdutoRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProdutoService {
    private ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> getProdutos() {
        return repository.findAll();
    }

    public Optional<Produto> getProdutoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Produto salvar(Produto produto) {

        return repository.save(produto);
    }

    @Transactional
    public void excluir(Produto produto) {
        Objects.requireNonNull(produto.getId());
        repository.delete(produto);
    }
}
