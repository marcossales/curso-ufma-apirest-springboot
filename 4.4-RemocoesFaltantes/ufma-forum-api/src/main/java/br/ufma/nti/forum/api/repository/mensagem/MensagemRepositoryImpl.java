package br.ufma.nti.forum.api.repository.mensagem;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import br.ufma.nti.forum.api.model.Mensagem;
import br.ufma.nti.forum.api.model.Mensagem_;
import br.ufma.nti.forum.api.model.Topico_;
import br.ufma.nti.forum.api.repository.filter.MensagemFilter;

public class MensagemRepositoryImpl implements MensagemRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Mensagem> filtrar(MensagemFilter mensagemFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Mensagem> criteriaQuery = builder.createQuery(Mensagem.class);
		
		Root<Mensagem> root = criteriaQuery.from(Mensagem.class);
		
		//cria as restricoes
		Predicate[] predicates = criarRestricoes(mensagemFilter, builder, root);
		criteriaQuery.where(predicates);
		
		TypedQuery<Mensagem> query = manager.createQuery(criteriaQuery);
		return query.getResultList();
		
	}

	private Predicate[] criarRestricoes(MensagemFilter mensagemFilter, CriteriaBuilder builder, Root<Mensagem> root) {
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		//where descricao like '%algum texto%'
		if(!StringUtils.isEmpty(mensagemFilter.getTexto())) {
			predicates.add(builder.like(
										builder.lower(root.get(Mensagem_.texto)), "%"+mensagemFilter.getTexto().toLowerCase()+"%" 
										)
						);
		}
		if(mensagemFilter.getDataPostagemDe() != null) {
			predicates.add(builder.greaterThanOrEqualTo(
													root.get(Mensagem_.postadoEm), mensagemFilter.getDataPostagemDe().atStartOfDay()
									)
						);
		}
		if(mensagemFilter.getDataPostagemAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(
										root.get(Mensagem_.postadoEm), mensagemFilter.getDataPostagemAte().atTime(23, 59, 59)
										)
					);
		}
		
		if(mensagemFilter.getTopico()!=null) {
			predicates.add(
					builder.equal(
							root.get(Mensagem_.topico).get(Topico_.codigo),mensagemFilter.getTopico()
							)
					);
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
