package com.example.blzapi.model.service;

import com.example.blzapi.model.entity.Colaborador;
import com.example.blzapi.model.repository.ColaboradorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ColaboradorService {
    private ColaboradorRepository repository;

    public ColaboradorService(ColaboradorRepository repository) {
        this.repository = repository;
    }

    public List<Colaborador> getColaboradors() {
        return repository.findAll();
    }

    public Optional<Colaborador> getColaboradorById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Colaborador salvar(Colaborador colaborador) {

        return repository.save(colaborador);
    }

    @Transactional
    public void excluir(Colaborador colaborador) {
        Objects.requireNonNull(colaborador.getId());
        repository.delete(colaborador);
    }
}
