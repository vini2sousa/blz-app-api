package com.example.blzapi.api.dto;


import com.example.blzapi.model.entity.Cargo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import org.modelmapper.ModelMapper;




public class CargoDTO {

    private long id;
    private String nome;
    private String descricao;
    private long idLoja;
    private String nomeLoja;

    public static CargoDTO create(Cargo cargo){

        ModelMapper moldelMapper = new ModelMapper();
        CargoDTO dto = moldelMapper.map(cargo, CargoDTO.class);
        return dto;

    }
}
