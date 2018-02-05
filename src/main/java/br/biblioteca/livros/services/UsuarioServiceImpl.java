package br.biblioteca.livros.services;

import br.biblioteca.livros.beans.Role;
import br.biblioteca.livros.beans.Usuario;
import br.biblioteca.livros.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Usuario usuario) {

        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));

        usuario.getRoles().add(new Role("ROLE_BASIC"));

        usuarioRepository.save(usuario);

    }

    @Override
    public Usuario findByUsername(String username) {

        return usuarioRepository.findByUsername(username);

    }

    @Override
    public List<Usuario> findAll() {

        return usuarioRepository.findAll();
    }



}
