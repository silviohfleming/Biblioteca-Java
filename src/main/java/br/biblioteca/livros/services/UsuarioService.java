package br.biblioteca.livros.services;

import br.biblioteca.livros.beans.Usuario;

import java.util.List;

public interface UsuarioService {

    void save(Usuario usuario);

    Usuario findByUsername(String username);

    List<Usuario> findAll();

}