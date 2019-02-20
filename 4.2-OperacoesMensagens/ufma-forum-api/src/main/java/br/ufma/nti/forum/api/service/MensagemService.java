package br.ufma.nti.forum.api.service;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.ufma.nti.forum.api.model.Categoria;
import br.ufma.nti.forum.api.model.Mensagem;
import br.ufma.nti.forum.api.model.Topico;
import br.ufma.nti.forum.api.repository.CategoriaRepository;
import br.ufma.nti.forum.api.repository.MensagemRepository;
import br.ufma.nti.forum.api.repository.TopicoRepository;
import br.ufma.nti.forum.api.service.exception.CategoriaInexistenteOuInativaException;
import br.ufma.nti.forum.api.service.exception.TopicoInexistenteOuInativoException;

@Service
public class MensagemService {
	
	@Autowired
	private MensagemRepository mensagemRepository;
	
	@Autowired
	private TopicoRepository topicoRepository;

	public Mensagem criar(Mensagem mensagem) {
		
		Topico topico = topicoRepository.findOne(mensagem.getTopico().getCodigo());
		if(topico == null || topico.isInativo() ) {
			throw new TopicoInexistenteOuInativoException();
		}
		
		mensagem.setPostadoEm(LocalDateTime.now());
		
		Mensagem mensagemSalva = mensagemRepository.save(mensagem);
		
		return mensagemSalva;
	}
	
	public Mensagem atualizar(Long codigo, Mensagem mensagem) {
		Mensagem mensagemSalva = mensagemRepository.findOne(codigo);
		if(mensagemSalva==null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(mensagem, mensagemSalva,"codigo");
		mensagemSalva.setPostadoEm(LocalDateTime.now());
		return mensagemRepository.save(mensagemSalva);
	}

	

}
