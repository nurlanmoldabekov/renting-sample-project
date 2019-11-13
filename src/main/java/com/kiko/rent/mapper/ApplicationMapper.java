package com.kiko.rent.mapper;

import com.kiko.rent.entity.ApplicationEntity;
import com.kiko.rent.model.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    ApplicationEntity toEntity(Application model);
    List<ApplicationEntity> toEntityList(List<Application> models);
    @Mappings({
            @Mapping(target="newUserId", source="entity.newUser.id"),
            @Mapping(target="oldUserId", source="entity.oldUser.id"),
            @Mapping(target="flatId", source="entity.flat.id")
    })
    Application toModel(ApplicationEntity entity);
    List<Application> toModelList(List<ApplicationEntity> entities);
}