package com.example.blzapi.model.service;

import com.example.blzapi.model.entity.ProdutoUtilizado;
import com.example.blzapi.model.repository.ProdutoUtilizadoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProdutoUtilizadoService {

    private final ProdutoUtilizadoRepository repository;

    public ProdutoUtilizadoService(ProdutoUtilizadoRepository repository) {
        this.repository = repository;
    }

    public List<ProdutoUtilizado> getProdutosUtilizados() {
        return repository.findAll();
    }

    public Optional<ProdutoUtilizado> getProdutoUtilizadoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public ProdutoUtilizado salvar(ProdutoUtilizado produtoUtilizado) {
        return repository.save(produtoUtilizado);
    }

    @Transactional
    public void excluir(ProdutoUtilizado produtoUtilizado) {
        Objects.requireNonNull(produtoUtilizado.getId());
        repository.delete(produtoUtilizado);
    }
}

