package org.ezand.telldus.rest.exception;

import static org.ezand.telldus.rest.dto.Result.fail;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.ezand.telldus.rest.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ResponseStatus(NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public Result handleNotFound() {
		return fail();
	}

	@ResponseStatus(INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Throwable.class)
	public Result handleGeneralException(final Throwable t) {
		LOG.error("Caught a general unhandled exception", t);
		return fail(t.getMessage());
	}
}
