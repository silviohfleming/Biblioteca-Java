package br.biblioteca.livros.validator;

import br.biblioteca.livros.beans.Usuario;
import br.biblioteca.livros.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UsuarioValidator implements Validator {

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

        if (usuario.getUsername().length() < 6 || usuario.getUsername().length() > 32 ) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (usuarioService.findByUsername(usuario.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        if (usuario.getPassword().length() < 8 || usuario.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!usuario.getPassword().equals(usuario.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }

    }
}
