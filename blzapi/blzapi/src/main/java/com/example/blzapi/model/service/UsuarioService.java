package com.example.blzapi.model.service;

import com.example.blzapi.exception.RegraNegocioException;
import com.example.blzapi.model.entity.Usuario;
import com.example.blzapi.model.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {
    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> getUsuarios() {
        return repository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        validar(usuario);
        return repository.save(usuario);
    }

    @Transactional
    public void excluir(Usuario usuario) {
        Objects.requireNonNull(usuario.getId());
        repository.delete(usuario);
    }
    public void validar(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome do usuário inválido");
        }
        if ((usuario.getTelefone() == null || usuario.getTelefone().trim().equals("")) &&
                (usuario.getCelular() == null || usuario.getCelular().trim().equals(""))) {
            throw new RegraNegocioException("Telefone ou celular deve ser informado");
        }
        if (usuario.getDataNascimento() == null || usuario.getDataNascimento().trim().equals("")) {
            throw new RegraNegocioException("Data de nascimento inválida");
        }
        if (usuario.getEmail() == null || usuario.getEmail().trim().equals("")) {
            throw new RegraNegocioException("Email inválido");
        }
        if (usuario.getCpf() == null || usuario.getCpf().trim().equals("")) {
            throw new RegraNegocioException("CPF inválido");
        }
        if (usuario.getSenha() == null || usuario.getSenha().trim().equals("")) {
            throw new RegraNegocioException("Senha inválida");
        }
    }
}
