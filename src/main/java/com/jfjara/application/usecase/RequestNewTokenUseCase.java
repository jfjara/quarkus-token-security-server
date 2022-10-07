package com.jfjara.application.usecase;

import com.jfjara.domain.usecase.IAuthenticateUseCase;

import javax.enterprise.context.ApplicationScoped;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

@ApplicationScoped
public class RequestNewTokenUseCase implements IAuthenticateUseCase {

    private final UserIsValidUseCase userIsValidUseCase;

    private final GenerateNewTokenUseCase generateNewTokenUseCase;

    private final RecoveryTokenByUserUseCase recoveryTokenUseCase;

    public RequestNewTokenUseCase(final UserIsValidUseCase userIsValidUseCase,
                                  final GenerateNewTokenUseCase generateNewTokenUseCase,
                                  final RecoveryTokenByUserUseCase recoveryTokenUseCase) {
        this.userIsValidUseCase = userIsValidUseCase;
        this.generateNewTokenUseCase = generateNewTokenUseCase;
        this.recoveryTokenUseCase = recoveryTokenUseCase;
    }


    public String execute(final String auth) {
        var tokenDecode = new String(Base64.getDecoder().decode(auth));
        var tokenSplitted = tokenDecode.split(":");
        var token = recoveryTokenUseCase.execute(tokenSplitted[0]);
        return token.orElseGet(() -> {
            try {
                return requestNewToken(tokenSplitted[0], tokenSplitted[1]);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private String requestNewToken(final String username, final String password) throws UnsupportedEncodingException {
        if (userIsValidUseCase.execute(username, password)) {
            return generateNewTokenUseCase.execute(username);
        }
        return null;
    }

}
