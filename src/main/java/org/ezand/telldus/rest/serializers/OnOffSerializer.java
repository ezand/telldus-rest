package org.ezand.telldus.rest.serializers;

import java.io.IOException;

import org.ezand.telldus.core.util.RichBoolean;
import org.ezand.telldus.rest.config.StateSwitchCase;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Serializer that serializes the state to either "on" or "off" if the state is a RichBoolean. Otherwise serialize the object normally.
 */
public class OnOffSerializer extends RichBooleanSerializer {
	public OnOffSerializer(final StateSwitchCase stateSwitchCase) {
		super(stateSwitchCase);
	}

	@Override
	public void serialize(final RichBoolean richBoolean, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeString(convertCase(richBoolean.asOnOff()));
	}
}
