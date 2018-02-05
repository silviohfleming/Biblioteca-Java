package br.biblioteca.livros.repository;


import br.biblioteca.livros.beans.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);

    List<Usuario> findAll();

}