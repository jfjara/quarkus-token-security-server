package com.jfjara.application.usecase;

import com.jfjara.domain.usecase.IAuthenticateUseCase;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class AuthenticateUseCase implements IAuthenticateUseCase {

    private final UserIsValidUseCase userIsValidUseCase;

    private final GenerateNewTokenUseCase generateNewTokenUseCase;

    private final RecoveryTokenUseCase recoveryTokenUseCase;

    public AuthenticateUseCase(final UserIsValidUseCase userIsValidUseCase,
                               final GenerateNewTokenUseCase generateNewTokenUseCase,
                               final RecoveryTokenUseCase recoveryTokenUseCase) {
        this.userIsValidUseCase = userIsValidUseCase;
        this.generateNewTokenUseCase = generateNewTokenUseCase;
        this.recoveryTokenUseCase = recoveryTokenUseCase;
    }


    public String execute(final String auth) {
        //decode auth -> user : pass
        var token = recoveryTokenUseCase.execute("jjb");
        return token.orElseGet(() -> requestNewToken("jjb", "1234"));
    }

    private String requestNewToken(final String username, final String password) {
        if (userIsValidUseCase.execute(username, password)) {
            return generateNewTokenUseCase.execute(username);
        }
        return null;
    }

}
