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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.ufma.nti.forum.api.model.Mensagem;
import br.ufma.nti.forum.api.model.Mensagem_;
import br.ufma.nti.forum.api.model.Topico_;
import br.ufma.nti.forum.api.repository.filter.MensagemFilter;
import br.ufma.nti.forum.api.repository.projection.ResumoMensagem;

public class MensagemRepositoryImpl implements MensagemRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Mensagem> filtrar(MensagemFilter mensagemFilter,Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Mensagem> criteriaQuery = builder.createQuery(Mensagem.class);
		
		Root<Mensagem> root = criteriaQuery.from(Mensagem.class);
		
		//cria as restricoes
		Predicate[] predicates = criarRestricoes(mensagemFilter, builder, root);
		criteriaQuery.where(predicates);
		
		TypedQuery<Mensagem> query = manager.createQuery(criteriaQuery);
		adicionarRestricoesPaginacao(query,pageable);
		
		return new PageImpl<Mensagem>(query.getResultList(),pageable,total(mensagemFilter));
		
	}
	
	@Override
	public Page<ResumoMensagem> resumir(MensagemFilter mensagemFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoMensagem> criteriaQuery = builder.createQuery(ResumoMensagem.class);
		Root<Mensagem> root = criteriaQuery.from(Mensagem.class);
		
		criteriaQuery.select(builder.construct(ResumoMensagem.class,
																	root.get(Mensagem_.texto),
																	root.get(Mensagem_.postadoEm),
																	root.get(Mensagem_.topico).get(Topico_.titulo)
											   )
				            );
		
		Predicate[] predicates = criarRestricoes(mensagemFilter, builder, root);
		criteriaQuery.where(predicates);
		
		TypedQuery<ResumoMensagem> query = manager.createQuery(criteriaQuery);
		adicionarRestricoesPaginacao(query,pageable);
		
		
		return new PageImpl<ResumoMensagem>(query.getResultList(),pageable,total(mensagemFilter));
	}

	private void adicionarRestricoesPaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual*totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
		
	}
	private Long total(MensagemFilter mensagemFilter) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<Mensagem> root = criteriaQuery.from(Mensagem.class);
		
		Predicate[] predicates = criarRestricoes(mensagemFilter, criteriaBuilder, root);
		
		criteriaQuery.where(predicates);
		
		criteriaQuery.select(criteriaBuilder.count(root));
		
		return manager.createQuery(criteriaQuery).getSingleResult();
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
