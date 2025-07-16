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

    private Long id;
    private String horario;
    private String dataAgendamento;
    private Long idLoja;
    private String nomeLoja;
    private Long idComanda;
    private Long idFuncionario;
    private String nomeFuncionario;
    private Long idCliente;
    private String nomeCliente;

    public static AgendamentoDTO create(Agendamento agendamento){

        ModelMapper modelMapper = new ModelMapper();
        AgendamentoDTO dto = modelMapper.map(agendamento, AgendamentoDTO.class);
        dto.nomeLoja = agendamento.getLoja().getNome();
        dto.nomeFuncionario = agendamento.getFuncionario().getNome();
        dto.nomeCliente = agendamento.getCliente().getUsuario().getNome();
        return dto;

    }
}
