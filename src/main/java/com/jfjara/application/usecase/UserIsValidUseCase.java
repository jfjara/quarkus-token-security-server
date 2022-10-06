package com.jfjara.application.usecase;

import com.jfjara.infraestructure.mongodb.repository.UserMongodbRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserIsValidUseCase {

    @Inject
    UserMongodbRepository userMongodbRepository;

    public boolean execute(final String user, final String pass) {
        var userRecovery = userMongodbRepository.getUser(user, pass);
        return userRecovery.isPresent();
    }

}
