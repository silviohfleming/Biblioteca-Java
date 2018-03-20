package br.biblioteca.livros.beans;

import br.biblioteca.livros.repository.AutorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutorTest {

    private Autor autor;

    @Autowired
    AutorRepository autorRepository;

    @Before
    public void criaAutor() {
        this.autor = new Autor();
    }

    @Test
    public void buscarTodosOsAutoresCadastrados() {

        Page<Autor> autores = this.autorRepository.findAll(new PageRequest(0, 10));
        assertThat(autores.getTotalElements()).isGreaterThan(1L);

    }

    @Test
    public void buscarAutorPeloNome() {

        Autor autorNaoEncontrado = this.autorRepository.findByNome("Carlos Drummond de Andrada");
        assertThat(autorNaoEncontrado).isNull();

        Autor autorEncontrado = this.autorRepository.findByNome("Ariano Suassuna");
        assertThat(autorEncontrado).isNotNull();
        assertThat(autorEncontrado.getNome()).isEqualTo("Ariano Suassuna");

    }

    @Test
    public void deveAdicionarNovoAutor()
    {
        criaAutor();
        autor.setId(4L);
        autor.setNome("Papito Love");
        autorRepository.save(autor);
    }

    @Test
    public void deveEditarAutorExistente()
    {
        autor = autorRepository.findByNome("Ariano Suassuna");
        autor.setNome("Jorge da Kombi");
        autorRepository.save(autor);
    }

    @Test
    public void deveExcluirAutorPeloNome()
    {
        criaAutor();
        autor.setId(4L);
        autor.setNome("Papito");
        autorRepository.save(autor);
        autor = autorRepository.findByNome("Papito");
        autorRepository.delete(autor);
        autor = autorRepository.findByNome("Papito");
        assertThat(autor).isNull();
    }

}
