package com.example.blzapi.model.service;

import com.example.blzapi.model.entity.CargoFuncionario;
import com.example.blzapi.model.repository.CargoFuncionarioRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CargoFuncionarioService {

    private CargoFuncionarioRepository repository;

    public CargoFuncionarioService(CargoFuncionarioRepository repository) { this.repository = repository; }
    public List<CargoFuncionario> getCargoFuncionarios() { return repository.findAll(); };
    public Optional<CargoFuncionario> getCargoFuncionariosById (Long id) { return repository.findById(id); };

    @Transactional
    public CargoFuncionario Salvar(CargoFuncionario cargoFuncionario) {
        return repository.save(cargoFuncionario);
    }
    @Transactional
    public void Excluir (CargoFuncionario cargoFuncionario) {
        Objects.requireNonNull(cargoFuncionario.getId());
        repository.delete(cargoFuncionario);
    }
}
