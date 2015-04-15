package org.ezand.telldus.rest.dto;

import static java.time.LocalDateTime.now;

import java.time.LocalDateTime;

public class ErrorResult extends Result<String> {
	private final LocalDateTime time;

	public ErrorResult() {
		this(null);
	}

	public ErrorResult(final String error) {
		this(now(), error);
	}

	public ErrorResult(final LocalDateTime time, final String error) {
		super(false, error);
		this.time = time;
	}

	public LocalDateTime getTime() {
		return time;
	}
}
