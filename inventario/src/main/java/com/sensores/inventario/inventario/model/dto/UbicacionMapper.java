package com.sensores.inventario.inventario.model.dto;
import com.sensores.inventario.inventario.model.entities.Ubicacion;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UbicacionMapper {

    UbicacionMapper Mapper = Mappers.getMapper( UbicacionMapper.class );

    UbicacionDto ubicacionToDto(Ubicacion ubicacion);

    Ubicacion toEntity(UbicacionDto ubicacionDTO);
}
