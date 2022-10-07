package com.jfjara.infraestructure.api.controller;

import com.jfjara.domain.usecase.IValidateUseCase;
import io.quarkus.vertx.http.runtime.CurrentVertxRequest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/validate")
public class ValidateController {

    @Inject
    private IValidateUseCase validateUseCase;

    @Inject
    private CurrentVertxRequest request;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String validate() {
        var auth = request.getCurrent().request().getHeader("My-Header-Auth");
        boolean execute = validateUseCase.execute(auth);
        if (execute) {
            return "validate OK";
        }
        return "Not valid";
    }
}
