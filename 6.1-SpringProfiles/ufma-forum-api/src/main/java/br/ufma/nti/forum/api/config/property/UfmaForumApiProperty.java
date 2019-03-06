package br.ufma.nti.forum.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("ufmaforum")
public class UfmaForumApiProperty {
	
	
	private final Seguranca seguranca = new Seguranca();
	
	
	public Seguranca getSeguranca() {
		return seguranca;
	}

	public static class Seguranca{
		
		private boolean enablehttps=false;
		private String originPermitida="http://localhost:8000";
		
		public boolean isEnablehttps() {
			return enablehttps;
		}
		public void setEnablehttps(boolean enablehttps) {
			this.enablehttps = enablehttps;
		}
		public String getOriginPermitida() {
			return originPermitida;
		}
		public void setOriginPermitida(String originPermitida) {
			this.originPermitida = originPermitida;
		}
		
		
	}

}
