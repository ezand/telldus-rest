package org.ezand.telldus.rest.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

// TODO Add read timeout
@Component
@ApplicationPath("/telldus")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		packages(
				"org.ezand.telldus.rest.providers",
				"org.ezand.telldus.rest.resources"
		);
	}
}
