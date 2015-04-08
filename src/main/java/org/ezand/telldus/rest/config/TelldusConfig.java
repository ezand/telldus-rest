package org.ezand.telldus.rest.config;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

import org.ezand.telldus.cli.repository.CliRepository;
import org.ezand.telldus.cli.repository.TelldusRepository;
import org.ezand.telldus.rest.settings.TelldusSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

@Configuration
public class TelldusConfig {

	@Bean
	@Autowired
	public TelldusRepository telldusRepository(final TelldusSettings settings) throws Exception {
		return new CliRepository(settings.getTdtool());
	}

	@Autowired
	@Bean
	@Primary
	public ObjectMapper objectMapper(final JacksonProperties properties) {
		final ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(NON_EMPTY);
		mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

		properties.getSerialization().entrySet().forEach(e ->
				mapper.configure(e.getKey(), properties.getSerialization().get(e.getKey())));

		properties.getDeserialization().entrySet().forEach(e ->
				mapper.configure(e.getKey(), properties.getDeserialization().get(e.getKey())));

		mapper.registerModules(
				new Jdk8Module(),
				new JSR310Module());
		return mapper;
	}
}
