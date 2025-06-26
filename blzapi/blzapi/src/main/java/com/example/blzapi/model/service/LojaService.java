package com.example.blzapi.model.service;

import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.repository.LojaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LojaService {
    private LojaRepository repository;

    public LojaService(LojaRepository repository) {
        this.repository = repository;
    }

    public List<Loja> getLojas() {
        return repository.findAll();
    }

    public Optional<Loja> getLojaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Loja salvar(Loja loja) {

        return repository.save(loja);
    }

    @Transactional
    public void excluir(Loja loja) {
        Objects.requireNonNull(loja.getId());
        repository.delete(loja);
    }
}
