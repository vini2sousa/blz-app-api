package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String celular;
    private String telefone;
    private String dataNascimento;
    private String login;
    private String senha;
    private String senhaRepeticao;
    private boolean admin;
    private List<Long> idLojas;

    public static UsuarioDTO create(Usuario usuario){

        ModelMapper modelMapper = new ModelMapper();
        UsuarioDTO dto = modelMapper.map(usuario, UsuarioDTO.class);
        return dto;
    };
}
