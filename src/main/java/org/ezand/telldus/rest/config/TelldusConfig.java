package org.ezand.telldus.rest.config;

import org.ezand.telldus.cli.repository.CliRepository;
import org.ezand.telldus.core.repository.TelldusRepository;
import org.ezand.telldus.rest.settings.TelldusSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelldusConfig {
	@Bean
	@Autowired
	public TelldusRepository telldusRepository(final TelldusSettings settings) throws Exception {
		return new CliRepository(settings.getTdtool());
	}
}
