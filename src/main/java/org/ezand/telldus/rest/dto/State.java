package org.ezand.telldus.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class State {
	private final String state;

	@JsonCreator
	public State(@JsonProperty("state") final String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	@Override
	public String toString() {
		return "State{" +
				"state='" + state + '\'' +
				'}';
	}
}
