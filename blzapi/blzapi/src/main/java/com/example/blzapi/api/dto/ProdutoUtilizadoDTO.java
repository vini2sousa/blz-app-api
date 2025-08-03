package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.ProdutoUtilizado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoUtilizadoDTO {

    private Long id;
    private Long idProduto;
    private String nomeProduto;
    private Long IdServico;
    private String nomeServico;
    private Integer quantidade;

    public static Object create(ProdutoUtilizado produtoUtilizado) {
        ModelMapper modelMapper = new ModelMapper();
        ProdutoUtilizadoDTO dto = modelMapper.map(produtoUtilizado, ProdutoUtilizadoDTO.class);
        dto.nomeProduto = produtoUtilizado.getProdutos().getNome();
        dto.nomeServico = produtoUtilizado.getServicos().getNome();
        return dto;
    }
}
