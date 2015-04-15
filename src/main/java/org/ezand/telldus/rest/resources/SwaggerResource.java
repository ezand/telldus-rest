package org.ezand.telldus.rest.resources;

import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

import com.wordnik.swagger.jersey.listing.ApiListingResourceJSON;

@Component
@Path("/api")
public class SwaggerResource extends ApiListingResourceJSON {
}
