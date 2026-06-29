package com.microservice.microservicio2.interfaces;

import java.util.List;

import org.mapstruct.Mapper;

import com.microservice.microservicio2.dto.ProductoDTO;
import com.microservice.microservicio2.entity.ProductoEntity;

@Mapper(componentModel = "spring")
public interface IProductoMapper {

	ProductoDTO entity2DTO(ProductoEntity ProductoEntity);

	ProductoEntity dTO2Entity(ProductoDTO ProductoDTO);

	List<ProductoDTO> toListDTO(Iterable<ProductoEntity> iterable);

}
