CREATE TABLE topico (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	titulo VARCHAR(100) NOT NULL,
	descricao VARCHAR(255) NULL,
	codigo_categoria BIGINT(20) NOT NULL,
	criado_em DATETIME NOT NULL,
	atualizado_em DATETIME NULL, 
	ativo BOOLEAN NOT NULL,
	CONSTRAINT fk_topico_categoria FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




INSERT INTO topico(titulo,descricao,codigo_categoria,criado_em,atualizado_em,ativo) values('Criação de API REST usando Spring Boot','Este tópico trata de dúvidas sobre o desenvolvimento de API REST com Spring Boot',1,NOW(),NULL,TRUE);
INSERT INTO topico(titulo,descricao,codigo_categoria,criado_em,atualizado_em,ativo) values('Desenvolvendo para Android com Flutter','Este tópico trata de dúvidas sobre o desenvolvimento de Aplicações para Android com Flutter',2,NOW(),NULL,TRUE);


CREATE TABLE mensagem(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	texto VARCHAR(500) NOT NULL,
	resposta_a BIGINT(20) NULL,
	postado_em DATETIME NOT NULL,
	codigo_topico BIGINT(20) NOT NULL,
	CONSTRAINT fk_mensagem_topico FOREIGN KEY (codigo_topico) REFERENCES topico(codigo),
	CONSTRAINT fk_mensagem_mensagem FOREIGN KEY (resposta_a) REFERENCES mensagem(codigo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO mensagem(texto,resposta_a,postado_em,codigo_topico) values ('Bem-vindo! Post aqui suas dúvidas sobre REST e Spring Boot.',NULL,NOW(),1);
INSERT INTO mensagem(texto,resposta_a,postado_em,codigo_topico) values ('Bem-vindo! Compartilhe as suas dúvidas e experiências com Ffutter.',NULL,NOW(),2);