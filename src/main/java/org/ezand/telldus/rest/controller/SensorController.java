package org.ezand.telldus.rest.controller;

import static org.ezand.telldus.rest.dto.Result.success;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;
import java.util.Optional;

import org.ezand.telldus.cli.data.Sensor;
import org.ezand.telldus.cli.repository.TelldusRepository;
import org.ezand.telldus.rest.dto.Result;
import org.ezand.telldus.rest.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "sensor")
public class SensorController {
	private final TelldusRepository repository;

	@Autowired
	public SensorController(final TelldusRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(value = {"", "/"}, method = GET)
	public Result<List<Sensor>> sensors() {
		return success(repository.getSensors());
	}

	@RequestMapping(value = "/{id:\\d*}", method = GET)
	public Result<Sensor> sensor(@PathVariable final int id) {
		return success(getDistinct(id).orElseThrow(NotFoundException::new));
	}

	private Optional<Sensor> getDistinct(@PathVariable final int id) {
		return repository.getSensors().stream().filter(d -> d.getId() == id).distinct().findFirst();
	}
}
