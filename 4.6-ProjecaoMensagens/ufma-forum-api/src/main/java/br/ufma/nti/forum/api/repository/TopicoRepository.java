package br.ufma.nti.forum.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufma.nti.forum.api.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

}
