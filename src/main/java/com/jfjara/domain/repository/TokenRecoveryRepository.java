package com.jfjara.domain.repository;

import com.jfjara.domain.model.UserDto;

import java.util.Optional;

public interface TokenRecoveryRepository {

    Optional<String> getUserToken(final String username);
    void saveToken(final String key, final String token);
}
