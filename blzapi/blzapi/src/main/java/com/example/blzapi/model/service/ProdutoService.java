package com.example.blzapi.model.service;

import com.example.blzapi.exception.RegraNegocioException;
import com.example.blzapi.model.entity.Produto;
import com.example.blzapi.model.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
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
        validar(produto);
        return repository.save(produto);
    }

    @Transactional
    public void excluir(Produto produto) {
        Objects.requireNonNull(produto.getId());
        repository.delete(produto);
    }
    public void validar(Produto produto) {
        if (produto.getNome() == null || produto.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome do produto inválido");
        }
        if (produto.getValorVenda() <= 0) {
            throw new RegraNegocioException("Valor de venda deve ser maior que zero");
        }
        if (produto.getUnidadeMedida() <= 0) {
            throw new RegraNegocioException("Unidade de medida inválida");
        }
        if (produto.getQuantidade() < 0) {
            throw new RegraNegocioException("Quantidade não pode ser negativa");
        }
        if (produto.getDataValidade() == null || produto.getDataValidade().trim().equals("")) {
            throw new RegraNegocioException("Data de validade inválida");
        }
        if (produto.getLoja() == null || produto.getLoja().getId() == null || produto.getLoja().getId() == 0) {
            throw new RegraNegocioException("Loja inválida");
        }
        if (produto.getFornecedor() == null || produto.getFornecedor().getId() == null || produto.getFornecedor().getId() == 0) {
            throw new RegraNegocioException("Fornecedor inválido");
        }
    }
}
