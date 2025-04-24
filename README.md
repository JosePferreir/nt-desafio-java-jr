# Livro API

**Autor:** José Pedro Ferreira da Silva  
**Telefone:** 51992502345  
**E-mail:** josepifer3008@gmail.com

API REST simples para gerenciamento de livros, construída com Spring Boot 3.4.4, 
Java 21 e banco de dados em memória H2. A aplicação roda na rota padrão 8080.


---
## Tecnologias Utilizadas
 - **Spring Boot:** utilizado por ser um framework com condiguração minima para criação de APIs REST,
  além de estar especificado na ordem do desafio.
 - **Spring Data JPA:** Utilizado para abstrair o acesso ao banco de dados, facilitando a implementação de repositórios.
 - **H2 Database:** Utilizado por ser um banco de dados em memoria e por também estar presente na ordem do desafio.
 - **DevTools:** Utilizado por facilitar o desenvolvimento, permitindo recarregar automaticamente as classes alteradas.
 - **Flyway:** Embora o banco de dados seja em memoria,
  utilizei o flyway para popular o banco inicialmente a cada inicialização.
 - **Lombok:** Utilizado para reduzir o boilerplate de código, como getters, setters e construtores. 
   - **Validation:** Utilizado para validar os dados de entrada,
  garantindo que os dados estejam corretos antes de serem processados.
 - **Mockito:** Utilizado para criar testes para simular o comportamento de dependências e verificar interações.
 - **MockMvc:** Utilizado para testar os endpoints da API, simulando requisições HTTP.

---

## Estrutura do Projeto

**Pacote principal:**

- **controller/**  
  Contém a classe que expõem os endpoints REST:  
  `LivroController`

- **dto/**  
  Comtém os objetos de transferência de dados (DTOs) para requisições e respostas:  
  `LivroDTO`, `LivroRequest`

- **exeption/**  
  Realiza o tratamento de erros e exceções customizadas:  
  `GlobalExceptionHandler`, `LivroNaoEncontradoException`

- **model/**  
  Comtém a entidade
  `Livro`

  - **repository/**  
    Contém a interface de acesso ao banco via Spring Data JPA:  
    `LivroRepository`

- **service/**  
  Comtém a classe que aplica as regras de negócio e transações:  
  `LivroService`

- **resources/db/migration**
  Script SQL de criação de tabela e dados iniciais

**Testes:**

- **src/test/java/com/exemplo/livro/controller/**  
  Testes de integração com MockMvc:  
  `LivroControllerIntegrationTest.java`

- **src/test/java/com/exemplo/livro/service/**  
  Testes unitários do service via Mockito:  
  `LivroServiceTest.java`

---

## Rotas da API
```text
GET    /livros/all  -   Lista todos os livros
GET    /livros/{id} -   Busca livro por ID (404 se não existir)
POST   /livros      -   Cria um novo livro
PUT    /livros/{id} -   Atualiza um livro existente (404 se não existir)
DELETE /livros/{id} -   Remove um livro (404 se não existir)
```

### Exemplos de Request com body

- **POST /livros**
    - **Body**:
      ```json
      {
        "titulo": "Senhor dos Aneis",
        "autor": "J.R.R. Tolkien",
        "anoPublicacao": 1954
      }
      ```
- **PUT /livros/1**
    - **Body**:
      ```json
      {
        "titulo": "Novo Titulo",
        "autor": "Autor",
        "anoPublicacao": 2025
      }
      ```

---

## Testes

- **Integração**:  
  Testes de integração com MockMvc para verificar os endpoints da API. 
  Sobem todo o contexto Spring e o banco de dados em memoria H2, junto com as migrations.
  Testam tanto o sucesso quanto o erro das requisições.

- **Unitários**:  
  Testes unitários do serviço com Mockito, verificando a lógica de negócio e as interações com o repositório.
  Testam tanto o sucesso quanto o erro das requisições.

