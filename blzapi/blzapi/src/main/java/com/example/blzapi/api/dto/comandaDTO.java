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
    private Intenger idTipoPagamento;
    private String nomeTipoPagamento;
    private String horario;
    private Intenger idAgendamento;
    private String nomeAgendamento;





    public static ComandaDTO create(Comanda comanda) {
        ModelMapper modelMapper = new ModelMapper();
        ComandaDTO dto = modelMapper.map(Comanda, ComandaDTO.class);
        return dto;
    }

}