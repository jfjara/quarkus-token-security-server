package com.jfjara.infraestructure.api.controller;

import com.jfjara.domain.usecase.IAuthenticateUseCase;
import com.jfjara.domain.usecase.IValidateUseCase;
import io.quarkus.vertx.http.runtime.CurrentVertxRequest;

import javax.inject.Inject;
import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/token")
public class SecurityController {

    @Inject
    private IAuthenticateUseCase authenticateUseCase;

    @Inject
    private CurrentVertxRequest request;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String requestToken() {
        var auth = request.getCurrent().request().getHeader("Auth");
        return authenticateUseCase.execute(auth);
    }



}
