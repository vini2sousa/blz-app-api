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

public class Cargo {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nome;
        private String descricao;
        @ManyToOne
        private Loja loja;

        @JsonIgnore
        @OneToMany(mappedBy = "cargo")
        private List<CargoFuncionario> funcionario;



}
