package br.biblioteca.livros.validator;

import br.biblioteca.livros.beans.Usuario;
import br.biblioteca.livros.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Usuario.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Usuario usuario = (Usuario) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Field.Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Field.Required");

        if (usuarioService.findByUsername(usuario.getUsername()) == null) {
            errors.rejectValue("username", "NotExist.userForm.username");
        }

    }
}
