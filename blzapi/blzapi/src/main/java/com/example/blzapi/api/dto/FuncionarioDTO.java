package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.Agendamento;
import com.example.blzapi.model.entity.Funcionario;
import com.example.blzapi.model.entity.Loja;
import org.modelmapper.ModelMapper;

public class FuncionarioDTO {
    private Loja loja;

    public static FuncionarioDTO create(Funcionario funcionario){

        ModelMapper modelMapper = new ModelMapper();
        FuncionarioDTO dto = modelMapper.map(funcionario, FuncionarioDTO.class);
        return dto;
    };
}
