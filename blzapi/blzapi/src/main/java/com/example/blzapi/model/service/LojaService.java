package com.example.blzapi.model.service;

import com.example.blzapi.exception.RegraNegocioException;
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
        validar(loja);
        return repository.save(loja);
    }

    @Transactional
    public void excluir(Loja loja) {
        Objects.requireNonNull(loja.getId());
        repository.delete(loja);
    }
    public void validar(Loja loja) {
        if (loja.getNome() == null || loja.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome da loja inválido");
        }
        if (loja.getEmail() == null || loja.getEmail().trim().equals("")) {
            throw new RegraNegocioException("Email da loja inválido");
        }
        if ((loja.getTelefone() == null || loja.getTelefone().trim().equals("")) &&
                (loja.getCelular() == null || loja.getCelular().trim().equals(""))) {
            throw new RegraNegocioException("Telefone ou celular da loja deve ser informado");
        }
        if (loja.getCnpj() == null || loja.getCnpj().trim().equals("")) {
            throw new RegraNegocioException("CNPJ da loja inválido");
        }
        if (loja.getDataCriacao() == null || loja.getDataCriacao().trim().equals("")) {
            throw new RegraNegocioException("Data de criação da loja inválida");
        }
        if (loja.getLogradouro() == null || loja.getLogradouro().trim().equals("")) {
            throw new RegraNegocioException("Logradouro inválido");
        }
        if (loja.getNumero() == null || loja.getNumero().trim().equals("")) {
            throw new RegraNegocioException("Número do endereço inválido");
        }
        if (loja.getBairro() == null || loja.getBairro().trim().equals("")) {
            throw new RegraNegocioException("Bairro inválido");
        }
        if (loja.getCidade() == null || loja.getCidade().trim().equals("")) {
            throw new RegraNegocioException("Cidade inválida");
        }
        if (loja.getEstado() == null || loja.getEstado().trim().equals("")) {
            throw new RegraNegocioException("Estado inválido");
        }
        if (loja.getCep() == null || loja.getCep().trim().equals("")) {
            throw new RegraNegocioException("CEP inválido");
        }
    }

}
