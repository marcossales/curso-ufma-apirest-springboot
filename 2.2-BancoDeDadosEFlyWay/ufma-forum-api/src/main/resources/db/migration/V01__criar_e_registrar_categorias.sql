CREATE TABLE categoria (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria(nome) values('Java');
INSERT INTO categoria(nome) values('Android');
INSERT INTO categoria(nome) values('Ciência da Computação');
INSERT INTO categoria(nome) values('Literatura');
INSERT INTO categoria(nome) values('Outros');