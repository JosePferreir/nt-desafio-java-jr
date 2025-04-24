package jose.nt.desafio.service;

import jakarta.transaction.Transactional;
import jose.nt.desafio.dto.LivroDTO;
import jose.nt.desafio.dto.LivroRequest;
import jose.nt.desafio.exception.LivroNaoEncontradoException;
import jose.nt.desafio.model.Livro;
import jose.nt.desafio.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    public List<LivroDTO> getAllLivros() {
        return LivroDTO.fromList(livroRepository.findAll());
    }

    public LivroDTO getLivroById(Long id) {
        return LivroDTO.fromEntity(livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id)));
    }
    @Transactional
    public LivroDTO createLivro(LivroRequest request) {
        Livro novoLivro = Livro.fromRequest(request);
        return LivroDTO.fromEntity(livroRepository.save(novoLivro));
    }
    @Transactional
    public LivroDTO updateLivro(Long id, LivroRequest request) {
        Livro update = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));
        update.updateFromRequest(request);
        return LivroDTO.fromEntity(livroRepository.save(update));
    }
    @Transactional
    public void deleteLivro(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id));
        livroRepository.delete(livro);
    }
}
