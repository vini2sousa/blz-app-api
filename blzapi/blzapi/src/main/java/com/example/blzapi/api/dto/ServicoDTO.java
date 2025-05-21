package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.Funcionario;
import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.entity.Servico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.ManyToOne;
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
    private Long idLoja;
    private String nomeLoja;

    public static ServicoDTO create(Servico servico){

        ModelMapper modelMapper = new ModelMapper();
        ServicoDTO dto = modelMapper.map(servico, ServicoDTO.class);
        dto.nomeLoja = servico.getLoja().getNome();
        return dto;
    };
}
