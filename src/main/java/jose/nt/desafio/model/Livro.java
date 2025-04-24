package jose.nt.desafio.model;

import jakarta.persistence.*;
import jose.nt.desafio.dto.LivroRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "livro")
public class Livro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private Integer anoPublicacao;


    public void updateFromRequest(LivroRequest req){
        this.titulo = req.titulo();
        this.autor = req.autor();
        this.anoPublicacao = req.anoPublicacao();
    }
    public static Livro fromRequest(LivroRequest livroRequest) {
        Livro livro = new Livro();
        livro.updateFromRequest(livroRequest);
        return livro;
    }

}
