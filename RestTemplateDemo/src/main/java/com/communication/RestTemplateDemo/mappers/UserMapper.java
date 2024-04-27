package com.communication.RestTemplateDemo.mappers;

import com.communication.RestTemplateDemo.dto.UserDto;
import com.communication.RestTemplateDemo.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
    User toUser(UserDto userDto);
}
