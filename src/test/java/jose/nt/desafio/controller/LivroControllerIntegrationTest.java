package jose.nt.desafio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import jose.nt.desafio.dto.LivroRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class LivroControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAlllivros() throws Exception {
        mockMvc.perform(get("/livros/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getLivroById() throws Exception {
        mockMvc.perform(get("/livros/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getLivroByIdNotFound() throws Exception {
        mockMvc.perform(get("/livros/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createLivro() throws Exception {
        String livro = objectMapper.writeValueAsString
                (new LivroRequest("Novo Livro", "Autor", 2025));

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(livro))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.titulo").value("Novo Livro"));
    }

    @Test
    void createLivroBadRequest() throws Exception {
        String livro = objectMapper.writeValueAsString
                (new LivroRequest("", "Autor", 2025));

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(livro))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.titulo").exists());
    }

    @Test
    void updateLivro() throws Exception {
        String livro = objectMapper.writeValueAsString
                (new LivroRequest("Livro Atualizado", "Autor Atualizado", 2025));

        mockMvc.perform(put("/livros/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(livro))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.titulo").value("Livro Atualizado"));
    }

    @Test
    void updateLivroNotFound() throws Exception {
        String livro = objectMapper.writeValueAsString
                (new LivroRequest("Livro Atualizado", "Autor Atualizado", 2025));

        mockMvc.perform(put("/livros/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(livro))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteLivro() throws Exception {
        mockMvc.perform(delete("/livros/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteLivroNotFound() throws Exception {
        mockMvc.perform(delete("/livros/99"))
                .andExpect(status().isNotFound());
    }
}
