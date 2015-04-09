package org.ezand.telldus.rest.exception;

import static org.ezand.telldus.rest.dto.Result.fail;

import org.ezand.telldus.rest.dto.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public Result handleNotFound() {
		return fail();
	}
}
