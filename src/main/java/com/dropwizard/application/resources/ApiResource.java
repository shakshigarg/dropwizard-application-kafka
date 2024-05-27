package com.dropwizard.application.resources;
import com.dropwizard.application.api.ServiceResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;



@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class ApiResource {
    Service service;

    @Inject
    public ApiResource() {
        service = new Service();
    }

    @GET
    @Path("/getDetails")
    public ServiceResponse getDetails(@QueryParam("param1") String param1, @QueryParam("param2") String param2) {
        try {
            String details = service.getDetails();
            return new ServiceResponse("200", details);
        } catch (Exception e) {
            return ServiceResponse.builder().status("400").message("fail message").build();
        }
    }
}
