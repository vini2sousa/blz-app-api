package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.ClienteLojaDTO;
import com.example.blzapi.model.entity.ClienteLoja;
import com.example.blzapi.model.service.ClienteLojaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/clientes") // Rota que o frontend está chamando
@RequiredArgsConstructor
@CrossOrigin
public class ClienteLojaController {

    private final ClienteLojaService service; // Assume que o serviço já existe

    @GetMapping()
    public ResponseEntity get() {
        // Busca todas as entidades de ligação ClienteLoja
        List<ClienteLoja> clientes = service.getClienteLojas();

        // Converte a lista de entidades para uma lista de DTOs e a retorna
        List<ClienteLojaDTO> dtos = clientes.stream()
                .map(ClienteLojaDTO::create)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
}
