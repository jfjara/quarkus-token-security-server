package com.jfjara.application.usecase;

import com.jfjara.domain.repository.TokenRecoveryRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class RecoveryTokenByUserUseCase {

    @Inject
    private TokenRecoveryRepository tokenRecoveryRepository;

    public Optional<String> execute(final String username) {
        return tokenRecoveryRepository.getUserToken(username);
    }
}
