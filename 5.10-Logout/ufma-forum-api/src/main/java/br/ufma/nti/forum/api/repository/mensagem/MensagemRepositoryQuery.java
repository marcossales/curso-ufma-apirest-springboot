package br.ufma.nti.forum.api.repository.mensagem;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.ufma.nti.forum.api.model.Mensagem;
import br.ufma.nti.forum.api.repository.filter.MensagemFilter;

public interface MensagemRepositoryQuery {
	
	public Page<Mensagem> filtrar(MensagemFilter mensagemFilter, Pageable pageable);

}
