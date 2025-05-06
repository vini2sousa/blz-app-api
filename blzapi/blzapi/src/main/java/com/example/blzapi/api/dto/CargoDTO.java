package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.Cargo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CargoDTO {


    private long id;

    private String nome;
    private String descricao;
    private Integer idLoja;
    private String nomeLoja;



    public static CargoDTO create(Cargo cargo) {
        ModelMapper modelMapper = new ModelMapper();
        CargoDTO dto = modelMapper.map(Cargo, CargoDTO.class);
        return dto;
    }

}