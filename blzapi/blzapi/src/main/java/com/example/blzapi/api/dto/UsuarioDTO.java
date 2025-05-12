package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UsuarioDTO {

    private long id;
    private String nome;
    private String telefone;
    private String celular;
    private String dataNascimento;
    private String email;
    private String cpf;
    private String senha;

    public static UsuarioDTO create(Usuario usuario) {
        ModelMapper modelMapper = new ModelMapper();
        UsuarioDTO dto = modelMapper.map(usuario, UsuarioDTO.class);
        return dto;
    }
}
