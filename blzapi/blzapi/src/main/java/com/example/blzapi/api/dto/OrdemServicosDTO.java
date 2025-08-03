package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.OrdemServicos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdemServicosDTO {

    private Long id;
    private Long idServico;
    private String nomeServico;
    private Long idAgendamento;
    private Integer quantidade;

    public static Object create(OrdemServicos ordemServicos){

        ModelMapper modelMapper = new ModelMapper();
        OrdemServicosDTO dto = modelMapper.map(ordemServicos, OrdemServicosDTO.class);
        dto.nomeServico = ordemServicos.getServicos().getNome();
        return dto;
    }

}
