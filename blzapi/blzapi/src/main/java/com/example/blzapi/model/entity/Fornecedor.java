package com.example.blzapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Fornecedor{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private boolean tipoEntidade;
        private String cnpj;
        private String cpf;
        private String nome;
        private String telefone;
        private String celular;
        private String email;
}
