package br.ufma.nti.forum.api.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Topico.class)
public abstract class Topico_ {

	public static volatile SingularAttribute<Topico, Long> codigo;
	public static volatile SingularAttribute<Topico, Boolean> ativo;
	public static volatile SingularAttribute<Topico, Categoria> categoria;
	public static volatile SingularAttribute<Topico, LocalDateTime> criadoEm;
	public static volatile SingularAttribute<Topico, LocalDateTime> atualizadoEm;
	public static volatile SingularAttribute<Topico, String> titulo;
	public static volatile SingularAttribute<Topico, String> descricao;

}

