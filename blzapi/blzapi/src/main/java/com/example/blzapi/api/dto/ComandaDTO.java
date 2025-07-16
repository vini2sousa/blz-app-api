package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.Comanda;
import com.example.blzapi.model.entity.FormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComandaDTO {
    private Long id;
    private Long idFormaPagamento;
    private String formaPagamento;
    private String horario;
    private Long idAgendamento;
    private String dataComanda;

    public static ComandaDTO create(Comanda comanda){

        ModelMapper modelMapper = new ModelMapper();
        ComandaDTO dto = modelMapper.map(comanda, ComandaDTO.class);
        dto.formaPagamento = comanda.getFormaPagamento().getNome();
        return dto;
    }
}
