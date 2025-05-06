package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColaboradorDTO {


    private long id;

    private String horario;
    private String data;
    private Loja loja;
    private Comanda comanda;
    private Usuario usuario;
    private Integer idCliente;
    private String nomeCliente;




    public static ColaboradorDTO create(Colaborador colaborador) {
        ModelMapper modelMapper = new ModelMapper();
        ColaboradorDTO dto = modelMapper.map(Colaborador, ColaboradorDTO.class);
        return dto;
    }

}