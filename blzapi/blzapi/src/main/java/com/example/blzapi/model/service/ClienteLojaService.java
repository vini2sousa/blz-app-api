package com.example.blzapi.model.service;

import com.example.blzapi.model.entity.ClienteLoja;
import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.entity.Usuario;
import com.example.blzapi.model.repository.ClienteLojaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteLojaService {

    private final ClienteLojaRepository repository;

    public ClienteLojaService(ClienteLojaRepository repository) {
        this.repository = repository;
    }

    public List<ClienteLoja> getClienteLojas() {
        return repository.findAll();
    }

    public Optional<ClienteLoja> getClienteLojaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public ClienteLoja salvar(ClienteLoja clienteLoja) {
        return repository.save(clienteLoja);
    }

    @Transactional
    public void excluir(ClienteLoja clienteLoja) {
        Objects.requireNonNull(clienteLoja.getId());
        repository.delete(clienteLoja);
    }
}

