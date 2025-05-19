package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.Funcionario;
import com.example.blzapi.model.entity.Usuario;
import org.modelmapper.ModelMapper;

public class UsuarioDTO {

    private String email;
    private String cpf;
    private String senha;

    public static UsuarioDTO create(Usuario usuario){

        ModelMapper modelMapper = new ModelMapper();
        UsuarioDTO dto = modelMapper.map(usuario, UsuarioDTO.class);
        return dto;
    };
}
