package org.ezand.telldus.rest.controller;

import static java.lang.String.valueOf;
import static org.ezand.telldus.cli.data.SwitchState.OFF;
import static org.ezand.telldus.cli.data.SwitchState.ON;
import static org.ezand.telldus.cli.data.Type.DIMMER;
import static org.ezand.telldus.cli.data.Type.SWITCH;
import static org.ezand.telldus.rest.dto.Result.fail;
import static org.ezand.telldus.rest.dto.Result.success;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Optional;

import org.ezand.telldus.cli.data.Device;
import org.ezand.telldus.cli.data.State;
import org.ezand.telldus.cli.data.Type;
import org.ezand.telldus.cli.repository.TelldusRepository;
import org.ezand.telldus.rest.dto.Result;
import org.ezand.telldus.rest.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO Add read timeout
@RestController
@RequestMapping(value = "device")
public class DeviceController {
	private final TelldusRepository repository;
	private final CacheManager cacheManager;

	@Autowired
	public DeviceController(final TelldusRepository repository, final CacheManager cacheManager) {
		this.repository = repository;
		this.cacheManager = cacheManager;
	}

	@RequestMapping(value = {"", "/"}, method = GET)
	public Result<List<Device>> devices() {
		return success(repository.getDevices());
	}

	@RequestMapping(value = "/{id:\\d*}", method = GET)
	public Result<Device> device(@PathVariable final int id) {
		return success(getDistinct(id).orElseThrow(NotFoundException::new));
	}

	@Cacheable(value = "stateCache")
	@RequestMapping(value = "/{id:\\d*}/state", method = GET)
	public Result<?> state(@PathVariable final int id) {
		return success(repository.getDeviceState(id));
	}

	@RequestMapping(value = "/{id:\\d*}/on", method = POST)
	public Result<State> turnOn(@PathVariable final int id) {
		final State state = repository.turnDeviceOn(id);
		updateStateCache(id, SWITCH, state.getState());
		return state.getState().equals(ON.lowerName()) ? success(state) : fail(state);
	}

	@RequestMapping(value = "/{id:\\d*}/off", method = POST)
	public Result<State> turnOff(@PathVariable final int id) {
		final State state = repository.turnDeviceOff(id);
		updateStateCache(id, SWITCH, state.getState());
		return state.getState().equals(OFF.lowerName()) ? success(state) : fail(state);
	}

	@RequestMapping(value = "/{id:\\d*}/dim/{level:\\d{1,3}}", method = POST)
	public Result<State> dim(@PathVariable final int id, @PathVariable final int level) {
		final State state = repository.dimDevice(id, level);
		updateStateCache(id, DIMMER, valueOf(state.getState()));
		return success(state);
	}

	private void updateStateCache(final int id, final Type type, final String state) {
		cacheManager.getCache("stateCache").put(id, new Result<>(true, new State(type, state)));
	}

	private Optional<Device> getDistinct(@PathVariable final int id) {
		return repository.getDevices().stream().filter(d -> d.getId() == id).distinct().findFirst();
	}
}
