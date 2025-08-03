package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.CargoFuncionario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CargoFuncionarioDTO {

    private Long id;
    private Long idCargo;
    private String nomeCargo;
    private Long idFuncionario;
    private String nomeFuncionario;

    public static Object create (CargoFuncionario cargoFuncionario) {

        ModelMapper modelMapper = new ModelMapper();
        CargoFuncionarioDTO dto = modelMapper.map(cargoFuncionario, CargoFuncionarioDTO.class);
        dto.nomeCargo = cargoFuncionario.getCargo().getNome();
        dto.nomeFuncionario = cargoFuncionario.getFuncionario().getNome();
        return dto;
    }

}
