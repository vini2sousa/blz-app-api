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

    // --- MÉTODO CREATE CORRIGIDO E SEGURO ---
    public static ClienteLojaDTO create(ClienteLoja clienteLoja){
        ModelMapper modelMapper = new ModelMapper();
        // Mapeia os campos com nomes iguais, como o 'id'
        ClienteLojaDTO dto = modelMapper.map(clienteLoja, ClienteLojaDTO.class);

        // Verificação de segurança para Loja
        if (clienteLoja.getLoja() != null) {
            dto.setIdLoja(clienteLoja.getLoja().getId());         // Popula o ID da loja
            dto.setNomeLoja(clienteLoja.getLoja().getNome());     // Popula o nome da loja
        }

        // Verificação de segurança para Usuário
        if (clienteLoja.getUsuario() != null) {
            dto.setIdUsuario(clienteLoja.getUsuario().getId());   // Popula o ID do usuário
            dto.setNomeUsuario(clienteLoja.getUsuario().getNome()); // Popula o nome do usuário
        }

        return dto;
    }
}