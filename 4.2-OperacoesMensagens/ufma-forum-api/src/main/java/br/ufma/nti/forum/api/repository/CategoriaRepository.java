package br.ufma.nti.forum.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufma.nti.forum.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
