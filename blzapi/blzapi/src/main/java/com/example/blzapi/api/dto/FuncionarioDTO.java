package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String dataNascimento;
    private String celular;
    private Long idLoja;
    private String nomeLoja;

    public static FuncionarioDTO create(Funcionario funcionario){

        ModelMapper modelMapper = new ModelMapper();
        FuncionarioDTO dto = modelMapper.map(funcionario, FuncionarioDTO.class);
        dto.nomeLoja = funcionario.getLoja().getNome();
        return dto;
    };
}
