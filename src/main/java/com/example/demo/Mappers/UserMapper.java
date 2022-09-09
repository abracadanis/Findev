package com.example.demo.Mappers;

import com.example.demo.Entities.UserEntity;
import com.example.demo.Services.so.UserInfo;
import com.example.demo.Services.so.UserInputSo;
import com.example.demo.Services.so.UserSo;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY,
        componentModel = "spring"
)
public interface UserMapper {

    UserEntity copy(UserEntity userEntity);

    UserEntity mapToEntity(UserInputSo userInputSo);

    UserInfo mapToInfo(UserEntity userEntity);

    UserSo mapToSo(UserEntity userEntity);
}
