package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.Comanda;
import com.example.blzapi.model.entity.Loja;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LojaDTO {
    private long id;
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

    public static LojaDTO create(Loja loja){

        ModelMapper modelMapper = new ModelMapper();
        LojaDTO dto = modelMapper.map(loja, LojaDTO.class);
        return dto;
    }
}
