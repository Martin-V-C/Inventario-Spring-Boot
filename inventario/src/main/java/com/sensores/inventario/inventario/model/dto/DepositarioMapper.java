package com.sensores.inventario.inventario.model.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sensores.inventario.inventario.model.entities.Depositario;

@Mapper
public interface DepositarioMapper {

    DepositarioMapper Mapper = Mappers.getMapper( DepositarioMapper.class );

    DepositarioDto deptoDto(Depositario dep);
    Depositario toEntity(DepositarioDto depositarioDTO);
}
