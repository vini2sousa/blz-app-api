package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.Colaborador;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColaboradorDTO {

    private Long id;
    private String nome;

    public static Object create(Colaborador colaborador) {
        ModelMapper modelMapper = new ModelMapper();
        FornecerdorDTO dto = modelMapper.map(colaborador,FornecerdorDTO.class);
        return dto;
    }
}
