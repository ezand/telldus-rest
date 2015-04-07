package org.ezand.telldus.rest.config;

import org.ezand.telldus.cli.repository.CliRepository;
import org.ezand.telldus.cli.repository.TelldusRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

@Configuration
public class TelldusConfig {
	@Bean
	public TelldusRepository telldusRepository() throws Exception {
		return new CliRepository();
	}

	@Bean
	public Module jdk8Module() {
		return new Jdk8Module();
	}

	@Bean
	public Module jsr310Module() {
		return new JSR310Module();
	}
}
