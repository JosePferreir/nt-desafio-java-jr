package jose.nt.desafio.controller;

import jakarta.validation.Valid;
import jose.nt.desafio.dto.LivroDTO;
import jose.nt.desafio.dto.LivroRequest;
import jose.nt.desafio.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroService livroService;

    @GetMapping("/all")
    public ResponseEntity<List<LivroDTO>> getAllLivros() {
        return ResponseEntity.ok(livroService.getAllLivros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> getLivroById(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.getLivroById(id));
    }

    @PostMapping
    public ResponseEntity<LivroDTO> createLivro(@RequestBody @Valid LivroRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.createLivro(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> updateLivro(@PathVariable Long id, @RequestBody @Valid LivroRequest request) {
        return ResponseEntity.ok(livroService.updateLivro(id, request));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivro(@PathVariable Long id) {
        livroService.deleteLivro(id);
        return ResponseEntity.noContent().build();
    }
}
