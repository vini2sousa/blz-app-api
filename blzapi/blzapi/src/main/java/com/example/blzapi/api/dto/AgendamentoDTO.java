package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.Agendamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.stream.Collectors;

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


    private List<OrdemServicoInputDTO> servicos;


    private List<OrdemServicosDTO> ordemServicos;

    @Data
    public static class OrdemServicoInputDTO {
        private Long idServico;
        private Integer quantidade;
    }

    public static AgendamentoDTO create(Agendamento agendamento){
        ModelMapper modelMapper = new ModelMapper();
        AgendamentoDTO dto = modelMapper.map(agendamento, AgendamentoDTO.class);

        // LÓGICA CORRIGIDA E SEGURA:
        // Adicionamos verificações para evitar erros se algum campo for nulo.
        if (agendamento.getLoja() != null) {
            dto.setNomeLoja(agendamento.getLoja().getNome());
        }
        if (agendamento.getFuncionario() != null) {
            dto.setNomeFuncionario(agendamento.getFuncionario().getNome());
        }
        if (agendamento.getCliente() != null && agendamento.getCliente() != null) {
            dto.setNomeCliente(agendamento.getCliente().getNome());
        }

        // Lógica para preencher a lista de serviços
        if (agendamento.getServico() != null && !agendamento.getServico().isEmpty()) {
            dto.ordemServicos = agendamento.getServico().stream()
                    .map(ordem -> (OrdemServicosDTO) OrdemServicosDTO.create(ordem))
                    .collect(Collectors.toList());
        }

        return dto;
    }
}