package com.jfjara.infraestructure.mongodb.repository;

import com.jfjara.domain.model.UserDto;
import com.jfjara.domain.repository.UserDatabaseRepository;
import com.jfjara.infraestructure.mongodb.mapper.UserMapper;
import com.jfjara.infraestructure.mongodb.model.UserEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class UserMongodbRepository implements UserDatabaseRepository {

    @Inject
    private UserMapper mapper;

    @Override
    public Optional<UserDto> getUser(final String username, final String password) {
        return Optional.ofNullable(mapper.toDto(UserEntity.getUser(username, password)));
    }

}
