package com.example.blzapi.api.dto;


import com.example.blzapi.model.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    private long id;

    private String nome;
    private float valorCompra;
    private float valorVenda;
    private int unidadeMedida;
    private float desconto;
    private int quantidade;
    private String dataValidade;
    private int quantidadeMin;
    private long idLoja;
    private String nomeLoja;
    private long idFornecedor;
    private String nomeFornecedor;

    public static ProdutoDTO create(Produto produto) {
        ModelMapper modelMapper = new ModelMapper();
        ProdutoDTO dto = modelMapper.map(produto, ProdutoDTO.class);
        return dto;
    }
}
