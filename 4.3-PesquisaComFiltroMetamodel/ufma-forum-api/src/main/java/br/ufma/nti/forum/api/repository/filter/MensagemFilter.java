package br.ufma.nti.forum.api.repository.filter;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class MensagemFilter {
	
	private String texto;
	
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate dataPostagemDe;
	
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate dataPostagemAte;
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public LocalDate getDataPostagemDe() {
		return dataPostagemDe;
	}
	public void setDataPostagemDe(LocalDate dataPostagemDe) {
		this.dataPostagemDe = dataPostagemDe;
	}
	public LocalDate getDataPostagemAte() {
		return dataPostagemAte;
	}
	public void setDataPostagemAte(LocalDate dataPostagemAte) {
		this.dataPostagemAte = dataPostagemAte;
	}

	

}
