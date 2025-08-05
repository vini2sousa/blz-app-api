package com.example.blzapi.model.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Usuario extends Pessoa {

    private String email;
    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<ClienteLoja> clienteLojas;
    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Venda> vendas;


}
