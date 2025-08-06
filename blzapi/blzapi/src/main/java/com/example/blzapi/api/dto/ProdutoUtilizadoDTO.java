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

    // CORREÇÃO: O tipo de retorno foi corrigido de Object para ProdutoUtilizadoDTO.
    public static ProdutoUtilizadoDTO create(ProdutoUtilizado produtoUtilizado) {
        ModelMapper modelMapper = new ModelMapper();
        ProdutoUtilizadoDTO dto = modelMapper.map(produtoUtilizado, ProdutoUtilizadoDTO.class);

        // Assumindo que a entidade ProdutoUtilizado tem os métodos getProdutos() e getServicos()
        if (produtoUtilizado.getProdutos() != null) {
            dto.nomeProduto = produtoUtilizado.getProdutos().getNome();
        }
        if (produtoUtilizado.getServicos() != null) {
            dto.nomeServico = produtoUtilizado.getServicos().getNome();
        }

        return dto;
    }
}