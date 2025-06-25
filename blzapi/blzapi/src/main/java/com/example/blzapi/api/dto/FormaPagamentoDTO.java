package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.FormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FormaPagamentoDTO {

    private long id;
    private String nome;
    private String descricao;

    public static FormaPagamentoDTO create(FormaPagamento formapagamento){

        ModelMapper modelMapper = new ModelMapper();
        FormaPagamentoDTO dto = modelMapper.map(formapagamento ,FormaPagamentoDTO.class);
        return dto;
        }
    }
