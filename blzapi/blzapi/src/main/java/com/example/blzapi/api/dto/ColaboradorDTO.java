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
    private String cpf;
    private String email;
    private String dataNascimento;
    private String celular;
    private Long idLoja;
    private String nomeLoja;
    private String telefone;

    public static Object create(Colaborador colaborador) {
        ModelMapper modelMapper = new ModelMapper();
        ColaboradorDTO dto = modelMapper.map(colaborador, ColaboradorDTO.class);
        dto.nomeLoja = colaborador.getLoja().getNome();
        return dto;
    }
}
