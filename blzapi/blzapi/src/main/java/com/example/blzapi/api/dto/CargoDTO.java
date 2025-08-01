package com.example.blzapi.api.dto;


import com.example.blzapi.model.entity.Cargo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import org.modelmapper.ModelMapper;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class CargoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Long idLoja;
    private String nomeLoja;

    public static CargoDTO create(Cargo cargo){

        ModelMapper modelMapper = new ModelMapper();
        CargoDTO dto = modelMapper.map(cargo, CargoDTO.class);
        dto.nomeLoja = cargo.getLoja().getNome();
        return dto;

    }
}
