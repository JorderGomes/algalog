CREATE TABLE vendedor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    email VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL
);
