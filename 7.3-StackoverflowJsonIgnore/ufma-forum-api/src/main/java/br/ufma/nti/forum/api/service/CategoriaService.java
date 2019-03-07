package br.ufma.nti.forum.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.ufma.nti.forum.api.model.Categoria;
import br.ufma.nti.forum.api.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria atualizar(Long codigo, Categoria categoria) {
		Categoria categoriaSalva = categoriaRepository.findOne(codigo);
		if(categoriaSalva==null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(categoria, categoriaSalva,"codigo");
		return categoriaRepository.save(categoriaSalva);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Categoria categoriaSalva = buscarCategoriaPeloCodigo(codigo);
		categoriaSalva.setAtivo(ativo);
		categoriaRepository.save(categoriaSalva);
	}

	private Categoria buscarCategoriaPeloCodigo(Long codigo) {
		Categoria categoriaSalva = categoriaRepository.findOne(codigo);
		if(categoriaSalva==null) {
			throw new EmptyResultDataAccessException(1);
		}
		return categoriaSalva;
	}

}
