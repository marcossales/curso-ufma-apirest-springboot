package br.ufma.nti.forum.api.service;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.ufma.nti.forum.api.model.Categoria;
import br.ufma.nti.forum.api.model.Topico;
import br.ufma.nti.forum.api.repository.CategoriaRepository;
import br.ufma.nti.forum.api.repository.TopicoRepository;
import br.ufma.nti.forum.api.service.exception.CategoriaInexistenteOuInativaException;

@Service
public class TopicoService {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Topico criar(Topico topico) {
		
		Categoria categoria = categoriaRepository.findOne(topico.getCategoria().getCodigo());
		if(categoria == null || categoria.isInativo() ) {
			throw new CategoriaInexistenteOuInativaException();
		}
		
		topico.setCriadoEm(LocalDateTime.now());
		
		Topico topicoSalvo = topicoRepository.save(topico);
		
		return topicoSalvo;
	}
	
	public Topico atualizar(Long codigo, Topico topico) {
		Topico topicoSalvo = topicoRepository.findOne(codigo);
		if(topicoSalvo==null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(topico, topicoSalvo,"codigo");
		topicoSalvo.setAtualizadoEm(LocalDateTime.now());
		return topicoRepository.save(topicoSalvo);
	}

	/*public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Topico topicoSalva = buscarTopicoPeloCodigo(codigo);
		topicoSalva.setAtivo(ativo);
		topicoRepository.save(topicoSalva);
	}*/

	/*private Topico buscarTopicoPeloCodigo(Long codigo) {
		Topico topicoSalvo = topicoRepository.findOne(codigo);
		if(topicoSalvo==null) {
			throw new EmptyResultDataAccessException(1);
		}
		return topicoSalvo;
	}*/

}
