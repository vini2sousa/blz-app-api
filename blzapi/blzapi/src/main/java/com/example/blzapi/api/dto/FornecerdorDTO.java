package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.Fornecedor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class FornecerdorDTO {

    private Long id;
    private boolean tipoEntidade;
    private String cnpj;
    private String cpf;
    private String nome;
    private String telefone;
    private String celular;

    public static  FornecerdorDTO create(Fornecedor fornecedor){

        ModelMapper modelMapper = new ModelMapper();
        FornecerdorDTO dto = modelMapper.map(fornecedor,FornecerdorDTO.class);
        return dto;
    }

}
