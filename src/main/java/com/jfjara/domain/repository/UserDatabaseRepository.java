package com.jfjara.domain.repository;

import com.jfjara.domain.model.UserDto;

import java.util.Optional;

public interface UserDatabaseRepository {

    Optional<UserDto> getUser(final String username, final String password);

}
