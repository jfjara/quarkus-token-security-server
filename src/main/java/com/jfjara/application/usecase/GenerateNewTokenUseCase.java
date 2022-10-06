package com.jfjara.application.usecase;

import com.jfjara.domain.repository.TokenRecoveryRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class GenerateNewTokenUseCase {

    private final TokenRecoveryRepository tokenRecoveryRepository;

    public GenerateNewTokenUseCase(final TokenRecoveryRepository tokenRecoveryRepository) {
        this.tokenRecoveryRepository = tokenRecoveryRepository;
    }

    public String execute(final String user) {
        var token = UUID.randomUUID().toString();
        tokenRecoveryRepository.saveToken(user, token);
        return token;
    }

}
