package br.ufma.nti.forum.api.service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ufma.nti.forum.api.model.Mensagem;
import br.ufma.nti.forum.api.model.Topico;
import br.ufma.nti.forum.api.repository.MensagemRepository;
import br.ufma.nti.forum.api.repository.TopicoRepository;
import br.ufma.nti.forum.api.repository.filter.MensagemFilter;
import br.ufma.nti.forum.api.repository.projection.ResumoMensagem;
import br.ufma.nti.forum.api.service.exception.TopicoInexistenteOuInativoException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class MensagemService {
	
	@Autowired
	private MensagemRepository mensagemRepository;
	
	@Autowired
	private TopicoRepository topicoRepository;

	public Mensagem criar(Mensagem mensagem) {
		
		Topico topico = topicoRepository.findOne(mensagem.getTopico().getCodigo());
		if(topico == null || topico.isInativo() ) {
			throw new TopicoInexistenteOuInativoException();
		}
		
		mensagem.setPostadoEm(LocalDateTime.now());
		
		Mensagem mensagemSalva = mensagemRepository.save(mensagem);
		
		return mensagemSalva;
	}
	
	public Mensagem atualizar(Long codigo, Mensagem mensagem) {
		
		
		Mensagem mensagemSalva = mensagemRepository.findOne(codigo);
		if(mensagemSalva==null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(mensagem, mensagemSalva,"codigo");
		mensagemSalva.setPostadoEm(LocalDateTime.now());
		return mensagemRepository.save(mensagemSalva);
	}

	
	public byte[] relatorioResumido(MensagemFilter mensagemFilter,Pageable pageable ) throws JRException {
		Page<ResumoMensagem> page = mensagemRepository.resumir(mensagemFilter, pageable);
		List<ResumoMensagem> lista = page.getContent();
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("REPORT_LOCALE",new Locale("pt","BR"));
		
		InputStream is = this.getClass().getResourceAsStream("/relatorios/resumo-mensagens.jasper");
		
		JasperPrint jasperPrint = JasperFillManager.fillReport(is, params,new JRBeanCollectionDataSource(lista));
		
		return JasperExportManager.exportReportToPdf(jasperPrint);
		
		
	}
	

}
