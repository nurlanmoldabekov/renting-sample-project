package com.kiko.rent.mapper;

import com.kiko.rent.entity.UserEntity;
import com.kiko.rent.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(User model);
    List<UserEntity> toEntityList(List<User> models);
    User toModel(UserEntity entity);
    List<User> toModelList(List<UserEntity> entities);
}