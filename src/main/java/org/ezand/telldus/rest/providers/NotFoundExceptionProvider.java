package org.ezand.telldus.rest.providers;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.status;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.ezand.telldus.rest.dto.ErrorResult;

@Provider
public class NotFoundExceptionProvider implements ExceptionMapper<NotFoundException> {
	@Override
	public Response toResponse(final NotFoundException exception) {
		return status(NOT_FOUND)
				.entity(new ErrorResult(exception.getMessage()))
				.build();
	}
}
