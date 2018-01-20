package br.biblioteca.livros.repository;

import br.biblioteca.livros.beans.Role;
import br.biblioteca.livros.beans.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {

    List<Usuario> usuarios = new ArrayList<>();

    UsuarioRepository() {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Usuario basic = new Usuario("teste", passwordEncoder.encode("123456"));
        basic.getRoles().add(new Role("ROLE_BASIC"));
        usuarios.add(basic);

        Usuario admin = new Usuario("admin", passwordEncoder.encode("123456"));
        admin.getRoles().add(new Role("ROLE_BASIC"));
        admin.getRoles().add(new Role("ROLE_ADMIN"));
        usuarios.add(admin);

    }

    public Usuario findByUsername(String username) {

        Usuario usuario = null;

        for (Usuario u : usuarios) {

            if (u.getUsername().equals(username)) {
                usuario = u;
            }

        }

        System.out.println("lido " + usuario);

        return usuario;
    }

    public void save(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("adicionado " + usuario);
    }


    public List<Usuario> findAll(){
        return usuarios;
    }

}

