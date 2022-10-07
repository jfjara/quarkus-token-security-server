package com.jfjara.application.usecase;

import com.jfjara.domain.repository.TokenRecoveryRepository;

import javax.enterprise.context.ApplicationScoped;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

@ApplicationScoped
public class GenerateNewTokenUseCase {

    private final TokenRecoveryRepository tokenRecoveryRepository;

    public GenerateNewTokenUseCase(final TokenRecoveryRepository tokenRecoveryRepository) {
        this.tokenRecoveryRepository = tokenRecoveryRepository;
    }

    public String execute(final String user) throws UnsupportedEncodingException {
        var token = UUID.randomUUID().toString();
        tokenRecoveryRepository.saveToken(user, token);
        token = user + ":" + token;
        return new String(Base64.getEncoder().encode(token.getBytes("UTF8")));
    }

}
