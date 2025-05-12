package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ColaboradorDTO {

    private long id;
    private String nome;
    private String telefone;
    private String celular;
    private String dataNascimento;
    private long idLoja;
    private String nomeLoja;

    public static ColaboradorDTO create(Colaborador colaborador){

        ModelMapper modelMapper = new ModelMapper();
        ColaboradorDTO dto = modelMapper.map(colaborador, ColaboradorDTO.class);
        return dto;
    }
}
