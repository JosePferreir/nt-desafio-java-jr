package jose.nt.desafio.exception;

import jakarta.persistence.EntityNotFoundException;

public class LivroNaoEncontradoException extends EntityNotFoundException {
    public LivroNaoEncontradoException(Long id) {
        super("Livro com ID " + id + " não encontrado.");
    }
}
