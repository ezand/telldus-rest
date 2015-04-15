package org.ezand.telldus.rest.config;

import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;

import org.ezand.telldus.rest.settings.SwaggerSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletContextAttributeExporter;

import com.wordnik.swagger.jersey.config.JerseyJaxrsConfig;
import com.wordnik.swagger.models.Contact;
import com.wordnik.swagger.models.Info;
import com.wordnik.swagger.models.License;
import com.wordnik.swagger.models.Swagger;

@Component
@Configuration
public class SwaggerConfig {
	@Autowired
	@Bean
	public ServletContextAttributeExporter contextAttributes(final SwaggerSettings settings) {
		final Map<String, Object> attributes = newHashMap();
		attributes.put("swagger", new Swagger()
				.info(new Info()
						.title(settings.getTitle())
						.contact(new Contact()
								.email(settings.getContact()))
						.license(new License()
								.name(settings.getLicense().getTitle())
								.url(settings.getLicense().getUrl()))
						.description(settings.getDescription())));

		final ServletContextAttributeExporter exporter = new ServletContextAttributeExporter();
		exporter.setAttributes(attributes);
		return exporter;
	}

	@Bean
	public ServletRegistrationBean swaggerServlet() {
		final ServletRegistrationBean servlet = new ServletRegistrationBean(new JerseyJaxrsConfig());
		servlet.setLoadOnStartup(1);
		return servlet;
	}
}
