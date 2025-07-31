package com.example.blzapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data


public class Colaborador extends Funcionario {

    @JsonIgnore
    @OneToMany(mappedBy = "colaborador")
    private List<Loja> lojas;
}
