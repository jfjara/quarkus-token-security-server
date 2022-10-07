package com.jfjara.application.usecase;

import com.jfjara.domain.repository.TokenRecoveryRepository;
import com.jfjara.domain.usecase.IValidateUseCase;

import javax.enterprise.context.ApplicationScoped;
import java.util.Base64;

@ApplicationScoped
public class ValidateTokenUseCase implements IValidateUseCase {

    private final TokenRecoveryRepository tokenRecoveryRepository;

    public ValidateTokenUseCase(final TokenRecoveryRepository tokenRecoveryRepository) {
        this.tokenRecoveryRepository = tokenRecoveryRepository;
    }

    public boolean execute(final String token) {
        var tokenDecode = new String(Base64.getDecoder().decode(token));
        var tokenSplitted = tokenDecode.split(":");
        return tokenRecoveryRepository.isValidToken(tokenSplitted[0], tokenSplitted[1]);
    }
}
