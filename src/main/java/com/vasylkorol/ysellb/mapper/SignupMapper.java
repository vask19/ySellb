package com.vasylkorol.ysellb.mapper;

import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.model.User;
import com.vasylkorol.ysellb.payload.request.SignupRequest;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SignupMapper {


    SignupMapper MAPPER = Mappers.getMapper(SignupMapper.class);

    User toUser(SignupRequest signupRequest);

    @InheritInverseConfiguration
    SignupRequest fromUser(User user);
}
