package com.example.blzapi.api.dto;

<<<<<<< HEAD
=======

>>>>>>> feat/criarDTO
import com.example.blzapi.model.entity.Cargo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
<<<<<<< HEAD
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
=======

import javax.persistence.Entity;
import org.modelmapper.ModelMapper;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor


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
>>>>>>> feat/criarDTO
