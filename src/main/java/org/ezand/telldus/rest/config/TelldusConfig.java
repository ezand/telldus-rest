package org.ezand.telldus.rest.config;

import org.ezand.telldus.cli.repository.CliRepository;
import org.ezand.telldus.core.repository.TelldusRepository;
import org.ezand.telldus.rest.serializers.OnOffSerializer;
import org.ezand.telldus.rest.serializers.RichBooleanSerializer;
import org.ezand.telldus.rest.settings.TelldusSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelldusConfig {
	@Value("${telldus.state.switch.serializer}")
	private Class<? extends RichBooleanSerializer> richBooleanSerializerClass = OnOffSerializer.class;
	@Value("${telldus.state.switch.case}")
	private StateSwitchCase stateSwitchCase = StateSwitchCase.LOWER;

	@Bean
	@Autowired
	public TelldusRepository telldusRepository(final TelldusSettings settings) throws Exception {
		return new CliRepository(settings.getTdtool());
	}

	@Bean
	public RichBooleanSerializer richBooleanSerializer() throws Exception {
		return richBooleanSerializerClass.getDeclaredConstructor(StateSwitchCase.class).newInstance(stateSwitchCase);
	}
}
