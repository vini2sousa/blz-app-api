package com.example.blzapi.model.service;

import com.example.blzapi.exception.RegraNegocioException;
import com.example.blzapi.model.entity.Comanda;
import com.example.blzapi.model.repository.ComandaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ComandaService {
    private ComandaRepository repository;

    public ComandaService(ComandaRepository repository) {
        this.repository = repository;
    }

    public List<Comanda> getComandas() {
        return repository.findAll();
    }

    public Optional<Comanda> getComandaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Comanda salvar(Comanda comanda) {
        validar(comanda);
        return repository.save(comanda);
    }

    @Transactional
    public void excluir(Comanda comanda) {
        Objects.requireNonNull(comanda.getId());
        repository.delete(comanda);
    }
    public  void validar(Comanda comanda) {
        if(comanda.getHorario() == null || comanda.getHorario().trim().equals("")){
            throw new RegraNegocioException("Horario Invalido");
        }
        if(comanda.getDataComanda() == null || comanda.getDataComanda().trim().equals("")){
            throw new RegraNegocioException("Data Invalida");
        }
        if (comanda.getFormaPagamento()== null || comanda.getFormaPagamento().getId()==null || comanda.getFormaPagamento().getId()==0) {
            throw new RegraNegocioException("Tipo Pagamento Invalido");
        }

    }
}
