package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.Fornecedor;
import com.example.blzapi.model.entity.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class FuncionarioDTO {

    private long id;
    private String nome;
    private String telefone;
    private String celular;
    private String dataNascimento;
    private long idLoja;
    private String nomeLoja;

    public static FuncionarioDTO create(Funcionario funcionario){

        ModelMapper modelMapper = new ModelMapper();
        FuncionarioDTO dto = modelMapper.map(funcionario, FuncionarioDTO.class);
        return dto;
    }
}

