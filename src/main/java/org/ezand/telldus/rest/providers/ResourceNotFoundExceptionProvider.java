package org.ezand.telldus.rest.providers;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.status;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.ezand.telldus.rest.dto.ErrorResult;
import org.ezand.telldus.rest.exception.ResourceNotFoundException;

@Provider
public class ResourceNotFoundExceptionProvider implements ExceptionMapper<ResourceNotFoundException> {
	@Override
	public Response toResponse(final ResourceNotFoundException exception) {
		return status(NOT_FOUND)
				.entity(new ErrorResult())
				.build();
	}
}
