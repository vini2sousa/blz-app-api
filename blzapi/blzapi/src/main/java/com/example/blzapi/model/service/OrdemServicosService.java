package com.example.blzapi.model.service;

import com.example.blzapi.model.entity.OrdemServicos;
import com.example.blzapi.model.repository.OrdemServicosRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrdemServicosService {

    private final OrdemServicosRepository repository;

    public OrdemServicosService(OrdemServicosRepository repository) {
        this.repository = repository;
    }

    public List<OrdemServicos> getOrdemServicos() {
        return repository.findAll();
    }

    public Optional<OrdemServicos> getOrdemServicoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public OrdemServicos salvar(OrdemServicos ordemServicos) {
        return repository.save(ordemServicos);
    }

    @Transactional
    public void excluir(OrdemServicos ordemServicos) {
        Objects.requireNonNull(ordemServicos.getId());
        repository.delete(ordemServicos);
    }
}

