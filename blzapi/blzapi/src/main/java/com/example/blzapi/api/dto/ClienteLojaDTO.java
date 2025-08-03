package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.ClienteLoja;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteLojaDTO {

    private Long id;
    private Long idUsuario;
    private String nomeUsuario;
    private Long idLoja;
    private String nomeLoja;

    public static Object create(ClienteLoja clienteLoja){
        ModelMapper Modelmapper = new ModelMapper();
        ClienteLojaDTO dto = Modelmapper.map(clienteLoja,ClienteLojaDTO.class);
        dto.nomeLoja = clienteLoja.getLoja().getNome();
        dto.nomeUsuario = clienteLoja.getUsuario().getNome();
        return dto;
    }
}
