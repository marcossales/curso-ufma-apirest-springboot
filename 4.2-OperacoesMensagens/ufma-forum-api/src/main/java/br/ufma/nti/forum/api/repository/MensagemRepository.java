package br.ufma.nti.forum.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufma.nti.forum.api.model.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Long>{

}
