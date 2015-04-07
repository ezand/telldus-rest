package org.ezand.telldus.rest.settings;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "telldus")
@Component
public class TelldusSettings {
	@NotEmpty
	private String tdtool;

	public String getTdtool() {
		return tdtool;
	}

	public void setTdtool(final String tdtool) {
		this.tdtool = tdtool;
	}
}
