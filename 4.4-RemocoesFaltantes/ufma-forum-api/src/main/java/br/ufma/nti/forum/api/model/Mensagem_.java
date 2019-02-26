package br.ufma.nti.forum.api.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Mensagem.class)
public abstract class Mensagem_ {

	public static volatile SingularAttribute<Mensagem, String> texto;
	public static volatile SingularAttribute<Mensagem, Long> codigo;
	public static volatile SingularAttribute<Mensagem, Topico> topico;
	public static volatile SingularAttribute<Mensagem, LocalDateTime> postadoEm;
	public static volatile SingularAttribute<Mensagem, Mensagem> respostaA;

}

