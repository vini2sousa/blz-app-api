package com.example.blzapi.model.entity;

import com.example.blzapi.model.entity.Agendamento;
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

public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String celular;
    private String cnpj;
    private String dataCriacao;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    @ManyToOne
    private Colaborador colaborador;

    @JsonIgnore
    @OneToMany(mappedBy = "loja")
    private List<ClienteLoja>  clientes ;
    @JsonIgnore
    @OneToMany(mappedBy = "loja")
    private List<Produto> produtos;
    @JsonIgnore
    @OneToMany(mappedBy = "loja")
    private List<Servico> servicos;
    @JsonIgnore
    @OneToMany (mappedBy = "loja")
    private List<Venda> vendas;
    @JsonIgnore
    @OneToMany(mappedBy = "loja")
    private List<Funcionario> funcionarios;
    @JsonIgnore
    @OneToMany(mappedBy = "loja")
    private List<Cargo> cargos;
    @JsonIgnore
    @OneToMany(mappedBy = "loja")
    private List<Agendamento> agendamentos;
}
