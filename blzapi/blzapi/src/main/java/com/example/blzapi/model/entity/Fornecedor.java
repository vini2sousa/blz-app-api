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

        @JsonIgnore
        @OneToMany(mappedBy = "fornecedor")
        private List<Produto> produto;
}
