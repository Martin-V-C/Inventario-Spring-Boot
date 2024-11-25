package com.sensores.inventario.inventario.model.entitiesDtos;

import com.sensores.inventario.inventario.model.entities.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RolMapper {

    RolMapper Mapper = Mappers.getMapper( RolMapper.class );

    Roldto roltoDto(Rol rol);
    Rol toEntity(Roldto rolDTO);
}
