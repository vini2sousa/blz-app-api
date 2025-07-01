package com.example.blzapi.model.service;

import com.example.blzapi.exception.RegraNegocioException;
import com.example.blzapi.model.entity.Cargo;
import com.example.blzapi.model.repository.CargoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CargoService {
    private CargoRepository repository;

    public CargoService(CargoRepository repository) {
        this.repository = repository;
    }

    public List<Cargo> getCargos() {
        return repository.findAll();
    }

    public Optional<Cargo> getCargoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Cargo salvar(Cargo cargo) {
        validar(cargo);
        return repository.save(cargo);
    }

    @Transactional
    public void excluir(Cargo cargo) {
        Objects.requireNonNull(cargo.getId());
        repository.delete(cargo);
    }
    public void validar(Cargo cargo) {
        if(cargo.getNome().trim().equals("") || cargo.getNome()==null) {
            throw new RegraNegocioException("Nome invalida");
        }
        if(cargo.getLoja() == null || cargo.getLoja().getId() == null || cargo.getLoja().getId()==0) {
            throw new RegraNegocioException("Loja invalida");
        }
    }
}
