package br.biblioteca.livros.controladores;

import br.biblioteca.livros.beans.Emprestimo;
import br.biblioteca.livros.beans.Livro;
import br.biblioteca.livros.beans.Usuario;
import br.biblioteca.livros.repository.EmprestimoRepository;
import br.biblioteca.livros.repository.LivroRepository;
import br.biblioteca.livros.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @GetMapping("/list")
    public ModelAndView emprestimos() {
        Iterable<Emprestimo> emprestimos = emprestimoRepository.findAll();
        return new ModelAndView("emprestimos/list", "emprestimos", emprestimos);
    }

    @GetMapping("/novo")
    public ModelAndView createForm(@ModelAttribute Emprestimo emprestimo) {

        ModelAndView modelAndView = new ModelAndView("emprestimos/form");

        Iterable<Livro> livros = livroRepository.findAll();
        modelAndView.addObject("livros", livros);

        Iterable<Usuario> usuarios = usuarioRepository.findAll();
        modelAndView.addObject("usuarios", usuarios);

        return modelAndView;
    }

}
