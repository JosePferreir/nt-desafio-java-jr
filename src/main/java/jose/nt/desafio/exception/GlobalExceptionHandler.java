package jose.nt.desafio.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LivroNaoEncontradoException.class)
    public ResponseEntity<String> handleLivroNaoEncontrado(LivroNaoEncontradoException e) {
        log.error("Livro não encontrado: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException e) {
        Map<String, String> erros = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(erro -> {
            erros.put(erro.getField(), erro.getDefaultMessage());
        });

        log.error("Erros de validação: {}", erros);
        return ResponseEntity
                .badRequest()
                .body(erros);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleJsonFormatError(HttpMessageNotReadableException e) {
        log.error("Erro ao ler JSON: {}", e.getMessage());
        return ResponseEntity
                .badRequest()
                .body("Erro na leitura do corpo da requisição. Verifique se o JSON está correto.");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        String expectedType = e.getRequiredType().getSimpleName();
        Object providedValue = e.getValue();

        String message = String.format(
                "Parâmetro inválido: esperava um valor do tipo '%s' mas recebeu '%s'",
                expectedType, providedValue
        );

        log.error(message);
        return ResponseEntity
                .badRequest()
                .body(message);
    }

}
