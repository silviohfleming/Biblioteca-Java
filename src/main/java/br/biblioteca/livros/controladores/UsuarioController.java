package br.biblioteca.livros.controladores;

import br.biblioteca.livros.beans.Usuario;
import br.biblioteca.livros.repository.UsuarioRepository;
import br.biblioteca.livros.services.SecurityService;
import br.biblioteca.livros.services.UsuarioService;
import br.biblioteca.livros.validator.LoginValidator;
import br.biblioteca.livros.validator.UsuarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioValidator usuarioValidator;

    @Autowired
    private LoginValidator loginValidator;

    @GetMapping("/autentication")
    public ModelAndView login() {

        return new ModelAndView("usuarios/form", "userForm", new Usuario());
    }

    @PostMapping("/autentication")
    public ModelAndView autentication(@ModelAttribute("userForm") Usuario userForm, BindingResult bindingResult, Model model) {

        loginValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ModelAndView("usuarios/form");
        }
        securityService.login(userForm.getUsername(), userForm.getPassword());
        return new ModelAndView("redirect:/usuarios/list");
    }

    @GetMapping("/list")
    public ModelAndView list() {
        return new ModelAndView("/usuarios/list");
    }

    @GetMapping("/listadmin")
    public ModelAndView listadmin(Usuario usuario) {

        List<Usuario> usuarios = usuarioService.findAll();
        return new ModelAndView("/usuarios/listadmin","usuarios", usuarios);
    }

    @GetMapping(value = "/registration")
    public ModelAndView registration() {

        return new ModelAndView("/usuarios/registration", "userForm", new Usuario());
    }

    @PostMapping(value = "/registration")
    public ModelAndView registrationform(@ModelAttribute("userForm") Usuario userForm, BindingResult bindingResult, Model model) {

        usuarioValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ModelAndView("usuarios/registration");
        }

        String password = userForm.getPassword();

        usuarioService.save(userForm);

        try {
            securityService.login(userForm.getUsername(), password);
            return new ModelAndView("redirect:/usuarios/list");

        } catch (Exception e) {

            return new ModelAndView("redirect:/usuarios/login");
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();
        if (session != null){
            session.invalidate();
        }

        return "redirect:/usuarios/autentication";
    }

}

