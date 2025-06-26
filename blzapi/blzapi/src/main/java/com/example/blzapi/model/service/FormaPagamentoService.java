package com.example.blzapi.model.service;

import com.example.blzapi.exception.RegraNegocioException;
import com.example.blzapi.model.entity.FormaPagamento;
import com.example.blzapi.model.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FormaPagamentoService {
    private FormaPagamentoRepository repository;

    public FormaPagamentoService(FormaPagamentoRepository repository) {
        this.repository = repository;
    }

    public List<FormaPagamento> getFormaPagamentos() {
        return repository.findAll();
    }

    public Optional<FormaPagamento> getFormaPagamentoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        validar(formaPagamento);
        return repository.save(formaPagamento);
    }

    @Transactional
    public void excluir(FormaPagamento formaPagamento) {
        Objects.requireNonNull(formaPagamento.getId());
        repository.delete(formaPagamento);
    }
    public void validar(FormaPagamento formaPagamento) {
        if (formaPagamento.getNome()==null || formaPagamento.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome Invalido");
        }
    }
}
