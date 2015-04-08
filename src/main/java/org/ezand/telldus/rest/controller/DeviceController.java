package org.ezand.telldus.rest.controller;

import static org.ezand.telldus.rest.dto.Result.success;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Optional;

import org.ezand.telldus.cli.data.Device;
import org.ezand.telldus.cli.repository.TelldusRepository;
import org.ezand.telldus.rest.dto.Result;
import org.ezand.telldus.rest.dto.State;
import org.ezand.telldus.rest.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO add cache??
@RestController
@RequestMapping(value = "device")
public class DeviceController {
	private final TelldusRepository repository;

	@Autowired
	public DeviceController(final TelldusRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(value = {"", "/"}, method = GET)
	public Result<List<Device>> devices() {
		return success(repository.getDevices());
	}

	@RequestMapping(value = "/{id:\\d*}", method = GET)
	public Result<Device> device(@PathVariable final int id) {
		return success(getDistinct(id).orElseThrow(NotFoundException::new));
	}

	@RequestMapping(value = "/{id:\\d*}/state", method = GET)
	public Result<State> state(@PathVariable final int id) {
		return success(new State(repository.getDeviceState(id)));
	}

	@RequestMapping(value = "/{id:\\d*}/on", method = POST)
	public Result<Boolean> turnOn(@PathVariable final int id) {
		return success(repository.turnDeviceOn(id));
	}

	@RequestMapping(value = "/{id:\\d*}/off", method = POST)
	public Result<Boolean> turnOff(@PathVariable final int id) {
		return success(repository.turnDeviceOff(id));
	}

	@RequestMapping(value = "/{id:\\d*}/dim/{level:\\d{1,3}}", method = POST)
	public Result<Integer> dim(@PathVariable final int id, @PathVariable final int level) {
		return success(repository.dimDevice(id, level));
	}

	private Optional<Device> getDistinct(@PathVariable final int id) {
		return repository.getDevices().stream().filter(d -> d.getId() == id).distinct().findFirst();
	}
}
