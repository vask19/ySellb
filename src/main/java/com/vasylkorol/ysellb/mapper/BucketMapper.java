package com.vasylkorol.ysellb.mapper;

import com.vasylkorol.ysellb.dto.BucketDto;
import com.vasylkorol.ysellb.dto.UserDto;
import com.vasylkorol.ysellb.model.Bucket;
import com.vasylkorol.ysellb.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BucketMapper
{
    BucketMapper MAPPER = Mappers.getMapper(BucketMapper.class);


    Bucket toBucket(BucketDto bucketDto);

    @InheritInverseConfiguration
    BucketDto fromBucket(Bucket bucket);


}
