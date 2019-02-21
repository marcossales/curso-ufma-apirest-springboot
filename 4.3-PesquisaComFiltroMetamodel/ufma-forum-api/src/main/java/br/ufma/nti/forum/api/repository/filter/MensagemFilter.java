package br.ufma.nti.forum.api.repository.filter;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class MensagemFilter {
	
	private String texto;
	
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private LocalDateTime dataPostagemDe;
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private LocalDateTime dataPostagemAte;
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public LocalDateTime getDataPostagemDe() {
		return dataPostagemDe;
	}
	public void setDataPostagemDe(LocalDateTime dataPostagemDe) {
		this.dataPostagemDe = dataPostagemDe;
	}
	public LocalDateTime getDataPostagemAte() {
		return dataPostagemAte;
	}
	public void setDataPostagemAte(LocalDateTime dataPostagemAte) {
		this.dataPostagemAte = dataPostagemAte;
	}

	

}
