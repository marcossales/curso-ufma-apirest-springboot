package br.ufma.nti.forum.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.ufma.nti.forum.api.config.property.UfmaForumApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(UfmaForumApiProperty.class)
public class UfmaForumApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UfmaForumApiApplication.class, args);
	}

}

