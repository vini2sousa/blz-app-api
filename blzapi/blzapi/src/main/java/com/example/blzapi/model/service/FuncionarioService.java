package com.example.blzapi.model.service;

import com.example.blzapi.exception.RegraNegocioException;
import com.example.blzapi.model.entity.Funcionario;
import com.example.blzapi.model.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FuncionarioService {
    private FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public List<Funcionario> getFuncionarios() {
        return repository.findAll();
    }

    public Optional<Funcionario> getFuncionarioById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Funcionario salvar(Funcionario funcionario) {
        validar(funcionario);
        return repository.save(funcionario);
    }

    @Transactional
    public void excluir(Funcionario funcionario) {
        Objects.requireNonNull(funcionario.getId());
        repository.delete(funcionario);
    }
    public void validar(Funcionario funcionario) {
        if (funcionario.getNome() == null || funcionario.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome do usuário inválido");
        }
        if ((funcionario.getTelefone() == null || funcionario.getTelefone().trim().equals("")) &&
                (funcionario.getCelular() == null || funcionario.getCelular().trim().equals(""))) {
            throw new RegraNegocioException("Telefone ou celular deve ser informado");
        }
        if (funcionario.getDataNascimento() == null || funcionario.getDataNascimento().trim().equals("")) {
            throw new RegraNegocioException("Data de nascimento inválida");
        }
        if(funcionario.getLoja()==null || funcionario.getLoja().getId()==null || funcionario.getLoja().getId()==0){
            throw new RegraNegocioException("Loja deve ser informado");
        }
    }
}
