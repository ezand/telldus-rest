package org.ezand.telldus.rest.serializers;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.lowerCase;
import static org.apache.commons.lang3.StringUtils.upperCase;

import org.ezand.telldus.core.util.RichBoolean;
import org.ezand.telldus.rest.config.StateSwitchCase;

import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public abstract class RichBooleanSerializer extends StdSerializer<RichBoolean> {
	protected final StateSwitchCase stateSwitchCase;

	protected RichBooleanSerializer(StateSwitchCase stateSwitchCase) {
		super(RichBoolean.class);
		this.stateSwitchCase = stateSwitchCase;
	}

	protected String convertCase(final String value) {
		switch (stateSwitchCase) {
			case LOWER:
				return lowerCase(value);
			case UPPER:
				return upperCase(value);
			default:
				return capitalize(value);
		}
	}
}
