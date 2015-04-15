package org.ezand.telldus.rest.providers;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

@Provider
public class ObjectMapperProvider implements ContextResolver<ObjectMapper> {
	private final ObjectMapper mapper;

	@Autowired
	public ObjectMapperProvider(final JacksonProperties properties) {
		final ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(NON_EMPTY);
		mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

		properties.getSerialization().entrySet().forEach(e ->
				mapper.configure(e.getKey(), properties.getSerialization().get(e.getKey())));

		properties.getDeserialization().entrySet().forEach(e ->
				mapper.configure(e.getKey(), properties.getDeserialization().get(e.getKey())));

		mapper.registerModules(
				new Jdk8Module(),
				new JSR310Module()
		);
		this.mapper = mapper;
	}

	@Override
	public ObjectMapper getContext(Class type) {
		return this.mapper;
	}
}
