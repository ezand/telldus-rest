package org.ezand.telldus.rest.config;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import org.ezand.telldus.cli.repository.CliRepository;
import org.ezand.telldus.cli.repository.TelldusRepository;
import org.ezand.telldus.rest.settings.TelldusSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

@Configuration
public class TelldusConfig {

	@Bean
	@Autowired
	public TelldusRepository telldusRepository(final TelldusSettings settings) throws Exception {
		return new CliRepository(settings.getTdtool());
	}

	@Bean
	@Primary
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.serializationInclusion(NON_EMPTY);
		return builder;
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
