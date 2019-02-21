package br.ufma.nti.forum.api.repository.mensagem;

import java.util.List;

import br.ufma.nti.forum.api.model.Mensagem;
import br.ufma.nti.forum.api.repository.filter.MensagemFilter;

public interface MensagemRepositoryQuery {
	
	public List<Mensagem> filtrar(MensagemFilter mensagemFilter);

}
