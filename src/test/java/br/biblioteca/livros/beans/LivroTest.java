package br.biblioteca.livros.beans;

import br.biblioteca.livros.repository.LivroRepository;
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
public class LivroTest {

    private Livro livro;

    @Autowired
    LivroRepository livroRepository;

    @Before
    public void criaLivro() {
        this.livro = new Livro();
    }

    @Test
    public void buscarTodosOsLivrosCadastrados() {

        Page<Livro> livros = this.livroRepository.findAll(new PageRequest(0, 10));
        assertThat(livros.getTotalElements()).isGreaterThan(1L);

    }

    @Test
    public void buscarLivroPeloNome() {

        Livro livroNaoEncontrado = this.livroRepository.findByNome("Sentimento do Munda");
        assertThat(livroNaoEncontrado).isNull();

        Livro livroEncontrado = this.livroRepository.findByNome("Sentimento do Mundo");
        assertThat(livroEncontrado).isNotNull();
        assertThat(livroEncontrado.getNome()).isEqualTo("Sentimento do Mundo");

    }

    @Test
    public void deveAdicionarNovoLivro()
    {
//        criaLivro();
//        livro.setId(4L);
//        livro.setNome("Problemas do Mundo Novo");
//        Autor autor = new Autor();
//        autor.setId(4L);
//        autor.setNome("Bingo Alvez");
//        livro.setAutor(autor);
//        livroRepository.save(livro);
    }

    @Test
    public void deveEditarLivroExistente()
    {
        livro = livroRepository.findByNome("Sentimento do Mundo");
        livro.setNome("Titulo do Livro");
        livroRepository.save(livro);
    }

    @Test
    public void deveExcluirLivroPeloNome()
    {
//        criaLivro();
//        livro.setId(4L);
//        livro.setNome("Problemas Ao Vento");
//        livroRepository.save(livro);
//        livro = livroRepository.findByNome("Sentimento do Mundo");
//        livroRepository.delete(livro);
//        livro = livroRepository.findByNome("Sentimento do Mundo");
//        assertThat(livro).isNull();
    }

}
