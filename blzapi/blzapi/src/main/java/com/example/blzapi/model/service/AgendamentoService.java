package com.example.blzapi.model.service;

import com.example.blzapi.exception.RegraNegocioException;
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
        validar(agendamento);
        return repository.save(agendamento);
    }

    @Transactional
    public void excluir(Agendamento agendamento) {
        Objects.requireNonNull(agendamento.getId());
        repository.delete(agendamento);
    }
    public void validar(Agendamento agendamento) {
        if (agendamento.getHorario() == null || agendamento.getHorario().trim().equals("")) {
            throw new RegraNegocioException("Horario inválida");
        }
        if (agendamento.getData() == null || agendamento.getData().trim().equals("")) {
            throw new RegraNegocioException("Data inválido");
        }
        if (agendamento.getLoja() == null || agendamento.getLoja().getId() == null || agendamento.getLoja().getId() == 0) {
            throw new RegraNegocioException("Loja inválido");
        }
        if (agendamento.getCliente() == null || agendamento.getCliente().getId() == null || agendamento.getCliente().getId() == 0) {
            throw new RegraNegocioException("Cliente inválido");
        }
        if (agendamento.getFuncionario() == null || agendamento.getFuncionario().getId() == null || agendamento.getFuncionario().getId() == 0) {
            throw new RegraNegocioException("Funcionario inválido");
        }

    }
}
