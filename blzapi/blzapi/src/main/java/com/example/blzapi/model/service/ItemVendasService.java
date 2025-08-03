package com.example.blzapi.model.service;

import com.example.blzapi.model.entity.ItemVendas;
import com.example.blzapi.model.repository.ItemVendasRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ItemVendasService {

    private final ItemVendasRepository repository;

    public ItemVendasService(ItemVendasRepository repository) {
        this.repository = repository;
    }

    public List<ItemVendas> getItemVendas() {
        return repository.findAll();
    }

    public Optional<ItemVendas> getItemVendasById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public ItemVendas salvar(ItemVendas itemVendas) {
        return repository.save(itemVendas);
    }

    @Transactional
    public void excluir(ItemVendas itemVendas) {
        Objects.requireNonNull(itemVendas.getId());
        repository.delete(itemVendas);
    }
}

