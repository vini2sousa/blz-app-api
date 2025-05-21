package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.Comanda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComandaDTO {
    private long id;
    private Long idFormaPagamento;
    private  String formaTipoPagamento;
    private String horario;
    private Long idAgendamento;

    public static ComandaDTO create(Comanda comanda){

        ModelMapper modelMapper = new ModelMapper();
        ComandaDTO dto = modelMapper.map(comanda, ComandaDTO.class);
        dto.formaTipoPagamento = comanda.getTipoPagamento().getNome();
        return dto;
    }
}
