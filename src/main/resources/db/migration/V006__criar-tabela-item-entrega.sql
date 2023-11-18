CREATE TABLE item_entrega (
    id SERIAL PRIMARY KEY,
    valor_unitario FLOAT NOT NULL,
    qtde INT NOT NULL,
    entrega_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    FOREIGN KEY (entrega_id) REFERENCES entrega(id),
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);