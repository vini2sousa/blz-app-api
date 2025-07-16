package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.Venda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaDTO {
    private Long id;


    private Long idFormaPagamento;
    private String nomeFormaPagamento;
    private String dataVenda;
    private String horario;
    private Long idLoja;
    private String nomeLoja;
    private Long idUsuario;
    private String nomeUsuario;

    public static VendaDTO create(Venda venda){

        ModelMapper modelMapper = new ModelMapper();
        VendaDTO dto = modelMapper.map(venda, VendaDTO.class);
        dto.nomeFormaPagamento = venda.getFormaPagamento().getNome();
        dto.nomeLoja = venda.getFormaPagamento().getNome();
        dto.nomeUsuario = venda.getUsuario().getNome();
        return dto;
    };

}
