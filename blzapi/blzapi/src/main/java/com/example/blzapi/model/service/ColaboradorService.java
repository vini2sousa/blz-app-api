package com.example.blzapi.model.service;

import com.example.blzapi.exception.RegraNegocioException;
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
        validar(colaborador);
        return repository.save(colaborador);
    }

    @Transactional
    public void excluir(Colaborador colaborador) {
        Objects.requireNonNull(colaborador.getId());
        repository.delete(colaborador);
    }
    public void validar(Colaborador colaborador) {
        if (colaborador.getNome() == null || colaborador.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome do usuário inválido");
        }
        if ((colaborador.getTelefone() == null || colaborador.getTelefone().trim().equals("")) &&
                (colaborador.getCelular() == null || colaborador.getCelular().trim().equals(""))) {
            throw new RegraNegocioException("Telefone ou celular deve ser informado");
        }
        if (colaborador.getDataNascimento() == null || colaborador.getDataNascimento().trim().equals("")) {
            throw new RegraNegocioException("Data de nascimento inválida");
        }
        if(colaborador.getLoja()==null || colaborador.getLoja().getId()==null || colaborador.getLoja().getId()==0){
            throw new RegraNegocioException("Loja deve ser informada");
        }
    }
}
