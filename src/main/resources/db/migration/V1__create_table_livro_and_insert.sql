CREATE TABLE livro(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    ano_publicacao INT NOT NULL
);
INSERT INTO livro (titulo, autor, ano_publicacao) VALUES ('1984', 'George Orwell', 1949),
                                                         ('Dom Casmurro', 'Machado de Assis', 1899),
                                                         ('O Pequeno Príncipe', 'Antoine de Saint-Exupéry', 1943),
                                                         ('A Revolução dos Bichos', 'George Orwell', 1945),
                                                         ('Memórias Póstumas de Brás Cubas', 'Machado de Assis', 1881);
