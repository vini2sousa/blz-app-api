package com.example.blzapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Funcionario extends Pessoa{

    @ManyToOne
    private Loja loja;

    @JsonIgnore
    @OneToMany(mappedBy = "funcionario")
    private List<CargoFuncionario> cargo;

}
