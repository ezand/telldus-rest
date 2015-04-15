package org.ezand.telldus.rest.providers;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.status;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.ezand.telldus.rest.dto.ErrorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class GeneralExceptionProvider implements ExceptionMapper<Throwable> {
	private static final Logger LOG = LoggerFactory.getLogger(GeneralExceptionProvider.class);

	@Override
	public Response toResponse(final Throwable throwable) {
		LOG.error("Caught unhandled general error", throwable);
		return status(INTERNAL_SERVER_ERROR)
				.entity(new ErrorResult(throwable.getMessage()))
				.build();
	}
}
