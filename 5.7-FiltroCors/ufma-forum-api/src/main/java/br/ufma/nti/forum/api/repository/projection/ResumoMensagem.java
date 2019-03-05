package br.ufma.nti.forum.api.repository.projection;

import java.time.LocalDateTime;

public class ResumoMensagem {
	private String texto;
	
	private LocalDateTime postadoEm;
	
	private String topico;

	
	public ResumoMensagem(String texto, LocalDateTime postadoEm, String topico) {
		this.texto = texto;
		this.postadoEm = postadoEm;
		this.topico = topico;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getPostadoEm() {
		return postadoEm;
	}

	public void setPostadoEm(LocalDateTime postadoEm) {
		this.postadoEm = postadoEm;
	}

	public String getTopico() {
		return topico;
	}

	public void setTopico(String topico) {
		this.topico = topico;
	}
	
	
}
