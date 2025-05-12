package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.Servico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ServicoDTO {

    private long id;
    private String nome;
    private int duracao;
    private int preco;
    private float comissao;
    private float desconto;
    private long IDLoja;
    private String nomeLoja;

    public static ServicoDTO create(Servico aluno) {
        ModelMapper modelMapper = new ModelMapper();
        ServicoDTO dto = modelMapper.map(aluno, ServicoDTO.class);
        return dto;
    }
}
