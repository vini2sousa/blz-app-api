package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.Funcionario;
import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.entity.Servico;
import org.modelmapper.ModelMapper;

import javax.persistence.ManyToOne;

public class ServicoDTO {
    private long id;
    private String nome;
    private int duracao;
    private int preco;
    private float comissao;
    private float desconto;
    private Loja loja;

    public static ServicoDTO create(Servico servico){

        ModelMapper modelMapper = new ModelMapper();
        ServicoDTO dto = modelMapper.map(servico, ServicoDTO.class);
        return dto;
    };
}
