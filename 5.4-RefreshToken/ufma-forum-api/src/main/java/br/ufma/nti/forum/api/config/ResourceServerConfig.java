package br.ufma.nti.forum.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration //duplicado, desnecessario
@EnableWebSecurity
@EnableResourceServer
public class ResourceServerConfig  extends ResourceServerConfigurerAdapter{// este ResourceServerConfig ficou no lugar  do antigo SecurityConfig e corresponde ao Resource Server do oauth2 
	
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("admin").password("admin").roles("ROLE");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		   .antMatchers("/categorias").permitAll()
		   .anyRequest().authenticated().and()
		   
		   .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		   .csrf().disable();
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.stateless(true);
	}
}
