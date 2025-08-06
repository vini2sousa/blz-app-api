package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.ItemVendas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemVendasDTO {

    private Long id;
    private Integer quantidade;
    private Long idvendas;
    private Long idProduto;
    private String nomeProduto;
    private Float precoUnitario;

    public static ItemVendasDTO create(ItemVendas itemVendas) {
        ModelMapper modelMapper = new ModelMapper();
        ItemVendasDTO dto = modelMapper.map(itemVendas, ItemVendasDTO.class);

        // Verificamos se o produto associado ao item não é nulo
        if (itemVendas.getProdutos() != null) {
            // --- LINHA FALTANTE ADICIONADA AQUI ---
            // Pega o ID do objeto Produto e o atribui ao campo idProduto da DTO.
            dto.setIdProduto(itemVendas.getProdutos().getId());

            // Buscamos o NOME do produto e colocamos no DTO (isso já estava certo)
            dto.setNomeProduto(itemVendas.getProdutos().getNome());
            // Buscamos o PREÇO do produto e colocamos no DTO (isso já estava certo)
            dto.setPrecoUnitario(itemVendas.getProdutos().getValorVenda());
        }

        // Boa prática: garantir que o ID da venda também seja mapeado
        if(itemVendas.getVendas() != null) {
            dto.setIdvendas(itemVendas.getVendas().getId());
        }

        return dto;
    }
}