package com.vasylkorol.ysellb.mapper;

import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    User toUser(UserDto userDto);

    @InheritInverseConfiguration
    UserDto fromUser(User user);

    List<User> toUserList(List<UserDto> userDtoList);

    List<UserDto> fromUserList(List<User> users);

}
