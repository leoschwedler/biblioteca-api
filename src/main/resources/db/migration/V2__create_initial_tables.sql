CREATE TABLE autor(
    id BIGSERIAL PRIMARY KEY ,
    nome VARCHAR(100) NOT NULL ,
    email VARCHAR(100) UNIQUE ,
    nacionalidade VARCHAR(100),
    data_nascimento DATE,
    criado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE livro(
                      id BIGSERIAL PRIMARY KEY ,
                      titulo VARCHAR(100) NOT NULL ,
                      ano_publicacao INTEGER,
                      paginas INTEGER,
                      preco NUMERIC(10,2),
                      status VARCHAR(15) NOT NULL ,
                      criado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE carteirinha(
                      id BIGSERIAL PRIMARY KEY ,
                      numero_carteirinha VARCHAR(100) UNIQUE NOT NULL ,
                      emitida_em TIMESTAMP NOT NULL ,
                      expira_em TIMESTAMP NOT NULL ,
                      ativa BOOLEAN DEFAULT TRUE
);

CREATE TABLE socio(
                      id BIGSERIAL PRIMARY KEY ,
                      id_carteirinha BIGINT NOT NULL UNIQUE REFERENCES carteirinha(id),
                      nome VARCHAR(100) NOT NULL ,
                      email VARCHAR(100) UNIQUE ,
                      telefone VARCHAR(100),
                      criado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE emprestimo(
                      id BIGSERIAL PRIMARY KEY ,
                      id_livro BIGINT NOT NULL REFERENCES livro(id),
                      id_socio BIGINT NOT NULL REFERENCES socio(id),
                      data_prevista_devolucao TIMESTAMP NOT NULL ,
                      data_devolucao TIMESTAMP,
                      status VARCHAR(100) NOT NULL ,
                      criado_em TIMESTAMP DEFAULT NOW()
);

CREATE TABLE autor_livro(

                           id_autor BIGINT NOT NULL REFERENCES autor(id),
                           id_livro BIGINT NOT NULL REFERENCES livro(id),
                           PRIMARY KEY (id_autor,id_livro)
);

CREATE INDEX idx_emprestimo_id_socio ON emprestimo(id_socio);
CREATE INDEX idx_emprestimo_id_livro ON emprestimo(id_livro);
CREATE INDEX idx_autor_livro_id_livro ON autor_livro(id_livro);

