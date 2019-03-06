package br.ufma.nti.forum.api.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.ufma.nti.forum.api.event.RecursoCriadoEvent;
import br.ufma.nti.forum.api.exceptionhandler.UfmaForumExceptionHandler.Erro;
import br.ufma.nti.forum.api.model.Topico;
import br.ufma.nti.forum.api.repository.TopicoRepository;
import br.ufma.nti.forum.api.service.TopicoService;
import br.ufma.nti.forum.api.service.exception.CategoriaInexistenteOuInativaException;

@RestController
@RequestMapping("/topicos")
public class TopicoResource {
	
	
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	
	@Autowired
	ApplicationEventPublisher publisher;
	
	@Autowired
	TopicoService topicoService;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TOPICO') and #oauth2.hasScope('read')")
	public List<Topico> listar(){
		return topicoRepository.findAll();
	}
	
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TOPICO') and #oauth2.hasScope('read')")
	public ResponseEntity<Topico> buscarPeloCodigo(@PathVariable Long codigo) {
		 Topico topico = topicoRepository.findOne(codigo);
		 return topico != null ? ResponseEntity.ok(topico) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_TOPICO') and #oauth2.hasScope('write')")
	public ResponseEntity<Topico> criar(@Valid @RequestBody Topico topico, HttpServletResponse response) {
		Topico topicoSalvo = topicoService.criar(topico);
		  
		publisher.publishEvent(new RecursoCriadoEvent(this, response, topicoSalvo.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(topicoSalvo);
		
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_TOPICO') and #oauth2.hasScope('write')")
	public ResponseEntity<Topico> atualizar(@PathVariable Long codigo,@Valid @RequestBody Topico topico){
		Topico topicoSalvo = topicoService.atualizar(codigo, topico);
		return ResponseEntity.ok(topicoSalvo);
	}
	
	@ExceptionHandler({CategoriaInexistenteOuInativaException.class})
	public ResponseEntity<Object> handleCategoriaInexistenteOuInativaException(CategoriaInexistenteOuInativaException ex){
		String mensagemUsuario = messageSource.getMessage("categoria.inexistente-ou-inativa",null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_TOPICO') and #oauth2.hasScope('write')")
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		topicoService.atualizarPropriedadeAtivo( codigo, ativo);
	}

	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_TOPICO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		topicoRepository.delete(codigo);
	}


}
	
	
	


