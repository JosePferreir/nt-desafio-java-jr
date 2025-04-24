package jose.nt.desafio.service;

import jose.nt.desafio.dto.LivroDTO;
import jose.nt.desafio.dto.LivroRequest;
import jose.nt.desafio.exception.LivroNaoEncontradoException;
import jose.nt.desafio.model.Livro;
import jose.nt.desafio.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {
    @Mock
    private LivroRepository livroRepository;

    @InjectMocks
    private LivroService livroService;

    private Livro livro;
    private LivroRequest livroRequest;

    @BeforeEach
    void setUp() {
        livroRequest = new LivroRequest("Livro teste", "Autor", 2025);
        livro = new Livro(1L, "Livro Teste", "Autor", 2025);
    }

    @Test
    void getAllLivros() {
        Livro newlivro = new Livro(1L, "Novo Livro", "Autor", 2025);
        given(livroRepository.findAll()).willReturn(List.of(livro, newlivro));

        List<LivroDTO> resultado = livroService.getAllLivros();

        then(livroRepository).should().findAll();
        assertThat(resultado.size()).isEqualTo(2);
        assertThat(resultado.get(0).titulo()).isEqualTo(livro.getTitulo());
        assertThat(resultado.get(1).titulo()).isEqualTo(newlivro.getTitulo());
    }

    @Test
    void getLivroById() {
        given(livroRepository.findById(1L)).willReturn(Optional.of(livro));

        LivroDTO resultado = livroService.getLivroById(1L);

        then(livroRepository).should().findById(1L);
        assertThat(resultado.titulo()).isEqualTo(livro.getTitulo());
        assertThat(resultado.id()).isEqualTo(1L);
    }

    @Test
    void getLivroByIdNotFound() {
        given(livroRepository.findById(99L)).willReturn(Optional.empty());

        assertThatThrownBy(() -> livroService.getLivroById(99L))
                .isInstanceOf(LivroNaoEncontradoException.class)
                .hasMessageContaining("99");
        then(livroRepository).should().findById(99L);
    }

    @Test
    void createLivro() {
        given(livroRepository.save(any(Livro.class))).willReturn(livro);

        LivroDTO resultado = livroService.createLivro(livroRequest);

        then(livroRepository).should().save(any(Livro.class));
        assertThat(resultado.id()).isEqualTo(1L);
    }

    @Test
    void updateLivro() {
        given(livroRepository.findById(1L)).willReturn(Optional.of(livro));
        given(livroRepository.save(any(Livro.class))).willReturn(livro);

        LivroRequest livroUpdate =
                new LivroRequest("Livro Atualizado", "Autor Atualizado", 2025);

        LivroDTO resultado = livroService.updateLivro(1L, livroUpdate);

        then(livroRepository).should().findById(1L);
        then(livroRepository).should().save(any(Livro.class));
        assertThat(resultado.titulo()).isEqualTo(livroUpdate.titulo());
    }

    @Test
    void updateLivroNotFound() {
        given(livroRepository.findById(99L)).willReturn(Optional.empty());

        assertThatThrownBy(() -> livroService.updateLivro(99L, livroRequest))
                .isInstanceOf(LivroNaoEncontradoException.class)
                .hasMessageContaining("99");
        then(livroRepository).should().findById(99L);
    }

    @Test
    void deleteLivro() {
        given(livroRepository.findById(1L)).willReturn(Optional.of(livro));

        livroService.deleteLivro(1L);

        then(livroRepository).should().findById(1L);
        then(livroRepository).should().delete(livro);
    }

    @Test
    void deleteLivroNotFound() {
        given(livroRepository.findById(99L)).willReturn(Optional.empty());

        assertThatThrownBy(() -> livroService.deleteLivro(99L))
                .isInstanceOf(LivroNaoEncontradoException.class)
                .hasMessageContaining("99");
        then(livroRepository).should().findById(99L);
    }
}
