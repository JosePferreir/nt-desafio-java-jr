package jose.nt.desafio.dto;
import jose.nt.desafio.model.Livro;

import java.util.List;

public record LivroDTO(Long id, String titulo, String autor, Integer anoPublicacao) {
    public static LivroDTO fromEntity(Livro livro){
        return new LivroDTO(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getAnoPublicacao());
    }
    public static List<LivroDTO> fromList(List<Livro> livros){
        return livros.stream().map(LivroDTO::fromEntity).toList();
    }
}
