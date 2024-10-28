package com.sensores.inventario.inventario.model.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sensores.inventario.inventario.model.entities.Bienes;


@Mapper
public interface BienMapper {

   BienMapper Mapper = Mappers.getMapper( BienMapper.class );

   BienesDto BienToDto(Bienes bien);
   //Bienes toEntity(BienesDto bienDTO);

}
