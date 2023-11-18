CREATE TABLE Produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    descricao VARCHAR(200) NOT NULL,
    preco FLOAT NOT NULL,
    qtdeEstoque INT NOT NULL,
    vendedor_id INT REFERENCES Vendedor(id)
);