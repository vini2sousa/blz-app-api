package com.example.blzapi.api.dto;

import com.example.blzapi.model.entity.ItemVendas;
import com.example.blzapi.model.entity.Venda;
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

    public static Object create(ItemVendas itemVendas){

        ModelMapper modelMapper = new ModelMapper();
        ItemVendasDTO dto = modelMapper.map(itemVendas, ItemVendasDTO.class);
        dto.nomeProduto = itemVendas.getProdutos().getNome();
        return dto;
    }

}
