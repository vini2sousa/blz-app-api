package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.Servico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List; // Importar List
import java.util.stream.Collectors; // Importar Collectors

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoDTO {
    private Long id;
    private String nome;
    private int duracao;
    private int preco;
    private float comissao;
    private float desconto;
    private Long idLoja;
    private String nomeLoja;
    private Long idCargo;
    private String nomeCargo;

    // MELHORIA: Adicionado campo para a lista de produtos utilizados.
    private List<ProdutoUtilizadoDTO> produtosUtilizados;

    public static ServicoDTO create(Servico servico){
        ModelMapper modelMapper = new ModelMapper();
        ServicoDTO dto = modelMapper.map(servico, ServicoDTO.class);

        if (servico.getLoja() != null) {
            dto.nomeLoja = servico.getLoja().getNome();
        }
        if (servico.getCargo() != null) {
            dto.nomeCargo = servico.getCargo().getNome();
        }

        // MELHORIA: Popula a lista de DTOs de produtos utilizados.
        if (servico.getProduto() != null) {
            dto.produtosUtilizados = servico.getProduto().stream()
                    .map(ProdutoUtilizadoDTO::create)
                    .collect(Collectors.toList());
        }

        return dto;
    };
}




