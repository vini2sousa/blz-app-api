package com.example.blzapi.model.service;

import com.example.blzapi.exception.RegraNegocioException;
import com.example.blzapi.model.entity.Servico;
import com.example.blzapi.model.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ServicoService {
    private ServicoRepository repository;

    public ServicoService(ServicoRepository repository) {
        this.repository = repository;
    }

    public List<Servico> getServico() {
        return repository.findAll();
    }

    public Optional<Servico> getServicoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Servico salvar(Servico servico) {
        validar(servico);
        return repository.save(servico);
    }

    @Transactional
    public void excluir(Servico servico) {
        Objects.requireNonNull(servico.getId());
        repository.delete(servico);
    }
    public void validar(Servico servico) {
        if (servico.getNome() == null || servico.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome do serviço inválido");
        }
        if (servico.getDuracao() <= 0) {
            throw new RegraNegocioException("Duração do serviço deve ser maior que zero");
        }
        if (servico.getPreco() <= 0) {
            throw new RegraNegocioException("Preço do serviço deve ser maior que zero");
        }
        if (servico.getComissao() < 0) {
            throw new RegraNegocioException("Comissão não pode ser negativa");
        }
        if (servico.getLoja() == null || servico.getLoja().getId() == null || servico.getLoja().getId() == 0) {
            throw new RegraNegocioException("Loja inválida");
        }
    }
}
