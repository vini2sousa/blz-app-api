package com.example.blzapi.api.controller;

import com.example.blzapi.api.dto.*;
import com.example.blzapi.exception.RegraNegocioException;
import com.example.blzapi.exception.SenhaInvalidaException;
import com.example.blzapi.model.entity.ClienteLoja;
import com.example.blzapi.model.entity.Loja;
import com.example.blzapi.model.entity.Usuario;
import com.example.blzapi.model.entity.Venda;
import com.example.blzapi.model.service.LojaService;
import com.example.blzapi.model.service.UsuarioService;

import com.example.blzapi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
@CrossOrigin
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final UsuarioService service;
    private final LojaService lojaService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;



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
    @GetMapping("/{id}/ClienteLojas")
    public ResponseEntity getClienteLojas(@PathVariable("id") Long id) {
        Optional<Usuario> usuario = service.getUsuarioById(id);
        if (!usuario.isPresent()) {
            return new ResponseEntity("Usuario não encontrado", HttpStatus.NOT_FOUND);
        }
        List<ClienteLoja> clienteLojas = usuario.get().getClienteLojas();
        return ResponseEntity.ok(clienteLojas.stream().map(ClienteLojaDTO::create).collect(Collectors.toList()));
    }
    @GetMapping("/{id}/vendas")
    public ResponseEntity getVendas(@PathVariable("id") Long id) {
        Optional<Usuario> usuario = service.getUsuarioById(id);
        if (!usuario.isPresent()) {
            return new ResponseEntity("Usuario não encontrado", HttpStatus.NOT_FOUND);
        }
        List<Venda> alunos = usuario.get().getVendas();
        return ResponseEntity.ok(alunos.stream().map(VendaDTO::create).collect(Collectors.toList()));
    }
    @PostMapping()
    public ResponseEntity post(@RequestBody UsuarioDTO dto) {
        try {
            if (dto.getSenha() == null || dto.getSenha().trim().equals("") ||
                    dto.getSenhaRepeticao() == null || dto.getSenhaRepeticao().trim().equals("")) {
                return ResponseEntity.badRequest().body("Senha inválida");
            }
            if (!dto.getSenha().equals(dto.getSenhaRepeticao())) {
                return ResponseEntity.badRequest().body("Senhas não conferem");
            }
            Usuario usuario = converter(dto);
            String senhaCriptografada = passwordEncoder.encode(dto.getSenha());
            usuario.setSenha(senhaCriptografada);
            usuario = service.salvar(usuario);
            return new ResponseEntity(usuario, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try{
            Usuario usuario = Usuario.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha()).build();
            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }


    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody UsuarioDTO dto) {
        if (!service.getUsuarioById(id).isPresent()) {
            return new ResponseEntity("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            if (dto.getSenha() == null || dto.getSenha().trim().equals("") ||
                    dto.getSenhaRepeticao() == null || dto.getSenhaRepeticao().trim().equals("")) {
                return ResponseEntity.badRequest().body("Senha inválida");
            }
            if (!dto.getSenha().equals(dto.getSenhaRepeticao())) {
                return ResponseEntity.badRequest().body("Senhas não conferem");
            }
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
    public Usuario converter(UsuarioDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Usuario usuario = modelMapper.map(dto, Usuario.class);

        // NOVO: Lógica para associar as Lojas ao Usuário
        if (dto.getIdLojas() != null && !dto.getIdLojas().isEmpty()) {
            List<ClienteLoja> lojasDoCliente = dto.getIdLojas().stream().map(idLoja -> {
                Loja loja = lojaService.getLojaById(idLoja)
                        .orElseThrow(() -> new RegraNegocioException("Loja não encontrada para o ID: " + idLoja));

                // Cria a entidade de ligação ClienteLoja
                ClienteLoja clienteLoja = new ClienteLoja();
                clienteLoja.setUsuario(usuario); // Associa ao usuário que estamos criando
                clienteLoja.setLoja(loja);       // Associa à loja encontrada
                return clienteLoja;
            }).collect(Collectors.toList());

            // A entidade Usuario deve ter um campo 'private List<ClienteLoja> lojas;'
            // com o @OneToMany correspondente
            usuario.setClienteLojas(lojasDoCliente);
        }

        return usuario;
    }
}
