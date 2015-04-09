package org.ezand.telldus.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Result<T> {
	private final boolean success;
	private final T result;

	@JsonCreator
	public Result(@JsonProperty("success") final boolean success, @JsonProperty("success") final T result) {
		this.success = success;
		this.result = result;
	}

	public T getResult() {
		return result;
	}

	public boolean isSuccess() {
		return success;
	}

	@Override
	public String toString() {
		return "Result{" +
				"result=" + result +
				", success=" + success +
				'}';
	}

	public static <T> Result<T> success() {
		return success(null);
	}

	public static <T> Result<T> success(final T result) {
		return new Result<>(true, result);
	}

	public static <T> Result<T> fail() {
		return fail(null);
	}

	public static <T> Result<T> fail(final T result) {
		return new Result<>(false, result);
	}
}
