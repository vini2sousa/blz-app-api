package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.FormaPagamento;
import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.entity.Usuario;
import com.example.blzapi.model.entity.Venda;
import org.modelmapper.ModelMapper;

import javax.persistence.ManyToOne;

public class VendaDTO {
    private long id;


    private FormaPagamento formaPagamento;
    private String data;
    private String horario;
    private Loja loja;
    private Usuario usuario;

    public static VendaDTO create(Venda venda){

        ModelMapper modelMapper = new ModelMapper();
        VendaDTO dto = modelMapper.map(venda, VendaDTO.class);
        return dto;
    };

}
