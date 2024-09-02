CREATE TABLE pessoa (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    identificador VARCHAR(50) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    tipo_identificador VARCHAR(50) NOT NULL,
    chave_pix VARCHAR (100) NOT NULL,
    valor_min_parcela_mensal DECIMAL(18,4) NOT NULL,
    valor_max_emprestimo DECIMAL(18,4) NOT NULL
);

CREATE TABLE emprestimo(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    id_pessoa INTEGER REFERENCES pessoa (id),
    valor_emprestimo DECIMAL(18,4) NOT NULL,
    numero_parcelas INTEGER NOT NULL,
    status_pagamento VARCHAR(50) NOT NULL,
    data_criacao DATE
);