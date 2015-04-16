package org.ezand.telldus.rest.resources;

import static org.ezand.telldus.rest.dto.Result.fail;
import static org.ezand.telldus.rest.dto.Result.success;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ezand.telldus.core.domain.Device;
import org.ezand.telldus.core.domain.State;
import org.ezand.telldus.core.repository.TelldusRepository;
import org.ezand.telldus.core.util.RichBoolean;
import org.ezand.telldus.rest.dto.Result;
import org.ezand.telldus.rest.exception.ResourceNotFoundException;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Component
@Path("/device")
@Produces({MediaType.APPLICATION_JSON})
@Api(value = "Device", description = "Endpoint to handle Telldus devices")
public class DeviceResource {
	private final TelldusRepository repository;
	private final CacheManager cacheManager;

	@Inject
	public DeviceResource(final TelldusRepository repository, final CacheManager cacheManager) {
		this.repository = repository;
		this.cacheManager = cacheManager;
	}

	@GET
	@ApiOperation(value = "All devices", notes = "List all available Telldus devices", responseContainer = "List", response = Device.class)
	public Result<List<Device>> devices() {
		return success(repository.getDevices());
	}

	@GET
	@Path("/{id:\\d*}")
	@ApiOperation(value = "Device", notes = "Get a specific device by id", response = Device.class)
	public Result<Device> device(@PathParam("id") @ApiParam(name = "id") final int id) {
		return success(getDistinct(id).orElseThrow(ResourceNotFoundException::new));
	}

	@Cacheable(value = "stateCache")
	@GET
	@Path(value = "/{id:\\d*}/state")
	@ApiOperation(value = "Device state", notes = "Get a specific device by id", response = State.class)
	public Result<State<?>> state(@PathParam("id") final int id) {
		return success(repository.getDeviceState(id));
	}

	@POST
	@Path("/{id:\\d*}/on")
	@ApiOperation(value = "Device switch on", notes = "Switch a specific device on", response = State.class)
	public Result<State<RichBoolean>> turnOn(@PathParam("id") final int id) {
		final State<RichBoolean> state = repository.turnDeviceOn(id);
		updateStateCache(id, state);
		return state.getState().isPositive() ? success(state) : fail(state);
	}

	@POST
	@Path(value = "/{id:\\d*}/off")
	@ApiOperation(value = "Device switch off", notes = "Switch a specific device off", response = State.class)
	public Result<State<RichBoolean>> turnOff(@PathParam("id") final int id) {
		final State<RichBoolean> state = repository.turnDeviceOff(id);
		updateStateCache(id, state);
		return state.getState().isPositive() ? success(state) : fail(state);
	}

	@POST
	@Path(value = "/{id:\\d*}/dim/{level:\\d{1,3}}")
	@ApiOperation(value = "Dim device", notes = "Dim a specific device to specified level", response = State.class)
	public Result<State<String>> dim(@PathParam("id") final int id, @PathParam("level") final int level) {
		final State<String> state = repository.dimDevice(id, level);
		updateStateCache(id, state);
		return success(state);
	}

	private void updateStateCache(final int id, final State<?> state) {
		cacheManager.getCache("stateCache").put(id, new Result<>(true, state));
	}

	private Optional<Device> getDistinct(@PathParam("id") final int id) {
		return repository.getDevices().stream().filter(d -> d.getId() == id).distinct().findFirst();
	}
}
