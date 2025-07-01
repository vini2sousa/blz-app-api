package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.UsuarioDTO;
import com.example.blzapi.exception.RegraNegocioException;
import com.example.blzapi.model.entity.Agendamento;
import com.example.blzapi.model.entity.Usuario;
import com.example.blzapi.model.service.UsuarioService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
@CrossOrigin
public class UsuarioController {

    private final UsuarioService service;


    @GetMapping()
    public ResponseEntity get() {
        List<Usuario> usuario = service.getUsuarios();
        return ResponseEntity.ok(usuario.stream().map(UsuarioDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Usuario> usuario = service.getUsuarioById(id);
        if (!usuario.isPresent()) {
            return new ResponseEntity("Usuario não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(usuario.map(UsuarioDTO::create));
    }
    @PostMapping()
    public ResponseEntity post(@RequestBody UsuarioDTO dto) {
        try {
            Usuario Usuario = converter(dto);
            Usuario = service.salvar(Usuario);
            return new ResponseEntity(Usuario, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody UsuarioDTO dto) {
        if (!service.getUsuarioById(id).isPresent()) {
            return new ResponseEntity("Usuario não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Usuario usuario = converter(dto);
            usuario.setId(id);
            service.salvar(usuario);
            return ResponseEntity.ok(usuario);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id){
        Optional<Usuario> usuario = service.getUsuarioById(id);
        if(!usuario.isPresent()){
            return new ResponseEntity("Usuario não encontrado", HttpStatus.NOT_FOUND);
        }try{
            service.excluir(usuario.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch(RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }
    public Usuario converter(UsuarioDTO dto){

        ModelMapper modelMapper = new ModelMapper();
        Usuario Usuario = modelMapper.map(dto,Usuario.class);
        return Usuario;
    }
}
