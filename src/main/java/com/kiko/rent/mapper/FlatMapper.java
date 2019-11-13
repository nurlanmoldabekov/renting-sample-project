package com.kiko.rent.mapper;

import com.kiko.rent.entity.FlatEntity;
import com.kiko.rent.model.Flat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlatMapper {
    FlatEntity toEntity(Flat model);
    List<FlatEntity> toEntityList(List<Flat> models);
    @Mappings({
            @Mapping(target="userId", source="entity.user.id"),
    })
    Flat toModel(FlatEntity entity);
    List<Flat> toModelList(List<FlatEntity> entities);
}