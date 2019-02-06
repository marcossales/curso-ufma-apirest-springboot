CREATE TABLE categoria (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	ativo BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria(nome,ativo) values('Java',TRUE);
INSERT INTO categoria(nome,ativo) values('Android',TRUE);
INSERT INTO categoria(nome,ativo) values('Ciência da Computação',TRUE);
INSERT INTO categoria(nome,ativo) values('Literatura',TRUE);
INSERT INTO categoria(nome,ativo) values('Outros',TRUE);