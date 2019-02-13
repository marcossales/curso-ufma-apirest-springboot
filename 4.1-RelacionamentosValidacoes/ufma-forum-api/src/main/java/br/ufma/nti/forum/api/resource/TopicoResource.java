package br.ufma.nti.forum.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufma.nti.forum.api.event.RecursoCriadoEvent;
import br.ufma.nti.forum.api.model.Categoria;
import br.ufma.nti.forum.api.model.Topico;
import br.ufma.nti.forum.api.repository.TopicoRepository;
import br.ufma.nti.forum.api.service.TopicoService;

@RestController
@RequestMapping("/topicos")
public class TopicoResource {
	
	
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	
	@Autowired
	ApplicationEventPublisher publisher;
	
	@Autowired
	TopicoService topicoService;
	
	@GetMapping
	public List<Topico> listar(){
		return topicoRepository.findAll();
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Topico> buscarPeloCodigo(@PathVariable Long codigo) {
		 Topico topico = topicoRepository.findOne(codigo);
		 return topico != null ? ResponseEntity.ok(topico) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Topico> criar(@Valid @RequestBody Topico topico, HttpServletResponse response) {
		Topico topicoSalvo = topicoService.criar(topico);
		  
		publisher.publishEvent(new RecursoCriadoEvent(this, response, topicoSalvo.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(topicoSalvo);
		
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Topico> atualizar(@PathVariable Long codigo,@Valid @RequestBody Topico topico){
		Topico topicoSalvo = topicoService.atualizar(codigo, topico);
		return ResponseEntity.ok(topicoSalvo);
	}
	
	

}
