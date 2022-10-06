package com.jfjara.infraestructure.api.controller;

import com.jfjara.domain.usecase.IAuthenticateUseCase;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/security")
public class SecurityController {

    @Inject
    private IAuthenticateUseCase authenticateUseCase;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String authorize() {
        return authenticateUseCase.execute("");
    }

}
