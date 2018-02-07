package br.biblioteca.livros.controladores;

import br.biblioteca.livros.beans.Emprestimo;
import br.biblioteca.livros.beans.Livro;
import br.biblioteca.livros.beans.Usuario;
import br.biblioteca.livros.repository.EmprestimoRepository;
import br.biblioteca.livros.repository.LivroRepository;
import br.biblioteca.livros.repository.UsuarioRepository;
import br.biblioteca.livros.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/list")
    public ModelAndView emprestimos() {
        Iterable<Emprestimo> emprestimos = emprestimoRepository.findAll();
        return new ModelAndView("emprestimos/list", "emprestimos", emprestimos);
    }

    @GetMapping("/novo")
    public ModelAndView createForm(@ModelAttribute Emprestimo emprestimo) {

        ModelAndView modelAndView = new ModelAndView("emprestimos/form");

        Iterable<Livro> livros = livroRepository.findAll();

        ArrayList<Livro> allBooks = new ArrayList<Livro>();

        Iterable<Usuario> usuarios = usuarioRepository.findAll();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nomeUsuario = authentication.getName();

        Usuario usuario = usuarioService.findByUsername(nomeUsuario);

        //Iterable<Emprestimo> emprestimos = (Iterable<Emprestimo>) usuario.getEmprestimos();
        Iterable<Emprestimo> emprestimos = emprestimoRepository.findAll();

        for (Livro book : livros) {
            boolean showBook = true;
            for (Emprestimo e : emprestimos) {
                if (e.getDataDevolucao() == null) {
                    if (book.getId() == e.getLivro().getId()) {
                        showBook = false;
                    }
                }
            }
            if (showBook) {
                allBooks.add(book);
            }
        }

        modelAndView.addObject("livros", allBooks);
        //modelAndView.addObject("allBooks", allBooks);
        //modelAndView.addObject("usuarios", usuarios);

        return modelAndView;
    }

    @GetMapping(value = "/emprestar/{id}")
    public ModelAndView emprestarForm(@PathVariable("id") Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nomeUsuario = authentication.getName();

        ArrayList<Livro> allBooks = new ArrayList<Livro>();
        Livro livro = this.livroRepository.findOne(id);

        Usuario usuario = usuarioService.findByUsername(nomeUsuario);
        Iterable<Emprestimo> emprestimos = (Iterable<Emprestimo>) usuario.getEmprestimos();

        try {
            Emprestimo emprestimo = new Emprestimo();

            long time = System.currentTimeMillis();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(time);

            emprestimo.setLivro(livro);
            emprestimo.setUsuario(usuario);
            emprestimo.setDataEmprestimo(timestamp);
            emprestimo.setDataDevolucao(null);

            emprestimo = emprestimoRepository.save(emprestimo);
            return new ModelAndView("redirect:/emprestimos/list");

        } catch (Exception e) {
            Iterable<Livro> livros = livroRepository.findAll();
            for (Livro book : livros) {
                boolean showBook = true;
                for (Emprestimo emp : emprestimos) {
                    if (emp.getDataDevolucao() == null) {
                        if (book.getId() == emp.getLivro().getId()) {
                            showBook = false;
                        }
                    }
                }
                if (showBook) {
                    allBooks.add(book);
                }
            }
            return new ModelAndView("emprestimo/novo", "livros", allBooks);
        }
    }


    @GetMapping(value = "/devolver/{id}")
    public ModelAndView devolverForm(@PathVariable("id") Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nomeUsuario = authentication.getName();

        ArrayList<Livro> allBooks = new ArrayList<Livro>();
        Emprestimo emprestimo = this.emprestimoRepository.findOne(id);

        Usuario usuario = usuarioService.findByUsername(nomeUsuario);

        try {

            long time = System.currentTimeMillis();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(time);

                if (emprestimo.getId() == id && emprestimo.getUsuario().getUsername() == usuario.getUsername() && emprestimo.getDataDevolucao() == null){

                    emprestimo.setDataDevolucao(timestamp);

                    emprestimo = emprestimoRepository.save(emprestimo);

                }

            return new ModelAndView("redirect:/emprestimos/list");

        } catch (Exception e) {
            return new ModelAndView("emprestimo/novo");
        }
    }


}
