package br.ufma.nti.forum.api.repository.mensagem;


import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.ufma.nti.forum.api.model.Mensagem;
import br.ufma.nti.forum.api.repository.filter.MensagemFilter;
import br.ufma.nti.forum.api.repository.projection.ResumoMensagem;

public interface MensagemRepositoryQuery {
	
	public Page<Mensagem> filtrar(MensagemFilter mensagemFilter, Pageable pageable);

	Page<ResumoMensagem> resumir(MensagemFilter mensagemFilter, Pageable pageable);
	
	public Long contarPostagensEmIntervaldoDeTempo(LocalDateTime inicio,LocalDateTime fim);

}
