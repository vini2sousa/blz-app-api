package com.example.blzapi.model.service;

import com.example.blzapi.exception.RegraNegocioException;
import com.example.blzapi.model.entity.Fornecedor;
import com.example.blzapi.model.repository.FornecedorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FornecedorService {
    private FornecedorRepository repository;

    public FornecedorService(FornecedorRepository repository) {
        this.repository = repository;
    }

    public List<Fornecedor> getFornecedor() {
        return repository.findAll();
    }

    public Optional<Fornecedor> getFornecedorById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Fornecedor salvar(Fornecedor fornecedor) {
        validar(fornecedor);
        return repository.save(fornecedor);
    }

    @Transactional
    public void excluir(Fornecedor fornecedor) {
        Objects.requireNonNull(fornecedor.getId());
        repository.delete(fornecedor);
    }
    public void validar(Fornecedor fornecedor) {
        if (fornecedor.getNome()==null || fornecedor.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome Invalido");
        }
        if (fornecedor.getCnpj()!=null){
            fornecedor.setCpf(null);
        }
        if ((fornecedor.getCnpj() == null || fornecedor.getCnpj().trim().equals("")) && (fornecedor.getCpf() == null || fornecedor.getCpf().trim().equals(""))) {
            throw new RegraNegocioException("CNPJ ou CPF não cadastrado");
        }
    }
}
