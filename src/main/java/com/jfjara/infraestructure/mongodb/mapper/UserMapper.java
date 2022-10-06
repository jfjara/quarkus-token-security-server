package com.jfjara.infraestructure.mongodb.mapper;

import com.jfjara.domain.model.UserDto;
import com.jfjara.infraestructure.mongodb.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface UserMapper {

    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    UserDto toDto(final UserEntity user);

}
