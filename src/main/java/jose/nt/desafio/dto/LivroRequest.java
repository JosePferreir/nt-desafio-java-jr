package jose.nt.desafio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroRequest(
        @NotBlank(message = "Título não pode ser vazio")
        String titulo,
        @NotBlank(message = "Autor não pode ser vazio")
        String autor,
        @NotNull(message = "Ano de publicação não pode ser nulo")
        Integer anoPublicacao){
}
