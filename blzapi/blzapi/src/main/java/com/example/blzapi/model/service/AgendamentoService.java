package com.example.blzapi.model.service;

import com.example.blzapi.model.entity.Agendamento;
import com.example.blzapi.model.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AgendamentoService {
    private AgendamentoRepository repository;

    public AgendamentoService(AgendamentoRepository repository) {
        this.repository = repository;
    }

    public List<Agendamento> getAgendamentos() {
        return repository.findAll();
    }

    public Optional<Agendamento> getAgendamentoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Agendamento salvar(Agendamento agendamento) {

        return repository.save(agendamento);
    }

    @Transactional
    public void excluir(Agendamento agendamento) {
        Objects.requireNonNull(agendamento.getId());
        repository.delete(agendamento);
}
}
