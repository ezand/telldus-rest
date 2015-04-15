package org.ezand.telldus.rest.resources;

import static org.ezand.telldus.rest.dto.Result.success;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ezand.telldus.core.domain.Sensor;
import org.ezand.telldus.core.repository.TelldusRepository;
import org.ezand.telldus.rest.dto.Result;
import org.ezand.telldus.rest.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Component
@Path("/sensor")
@Produces({MediaType.APPLICATION_JSON})
@Api(value = "Sensor", description = "Endpoint to handle Telldus sensors")
public class SensorResource {
	private final TelldusRepository repository;

	@Inject
	public SensorResource(final TelldusRepository repository) {
		this.repository = repository;
	}

	@GET
	@ApiOperation(value = "All sensors", notes = "List all available Telldus sensors", responseContainer = "List", response = Sensor.class)
	public Result<List<Sensor>> sensors() {
		return success(repository.getSensors());
	}

	@GET
	@Path("/{id:\\d*}")
	@ApiOperation(value = "Sensor", notes = "Get a specific sensor by id", response = Sensor.class)
	public Result<Sensor> sensor(@PathParam("id") @ApiParam(name = "id") final int id) {
		return success(getDistinct(id).orElseThrow(ResourceNotFoundException::new));
	}

	private Optional<Sensor> getDistinct(@PathParam("id") final int id) {
		return repository.getSensors().stream().filter(d -> d.getId() == id).distinct().findFirst();
	}
}
