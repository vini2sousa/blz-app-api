package com.example.blzapi.api.dto;


import com.example.blzapi.model.entity.Agendamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoDTO {

    private long id;
    private String horario;
    private String data;
    private Long idLoja;
    private String nomeLoja;
    private Long idComanda;
    private Long idUsuario;
    private String nomeUsuario;
    private Long idCliente;
    private String nomeCliente;

    public static AgendamentoDTO create(Agendamento agendamento){

        ModelMapper modelMapper = new ModelMapper();
        AgendamentoDTO dto = modelMapper.map(agendamento, AgendamentoDTO.class);
        dto.nomeLoja = agendamento.getLoja().getNome();
        return dto;

    }
}
