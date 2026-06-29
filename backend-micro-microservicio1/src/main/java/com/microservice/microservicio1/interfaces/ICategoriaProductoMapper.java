package com.microservice.microservicio1.interfaces;

import java.util.List;

import org.mapstruct.Mapper;

import com.microservice.microservicio1.dto.CategoriaProductoDTO;
import com.microservice.microservicio1.entity.CategoriaProductoEntity;

@Mapper(componentModel = "spring")
public interface ICategoriaProductoMapper {

	CategoriaProductoDTO entity2DTO(CategoriaProductoEntity categoriaProductoEntity);

	CategoriaProductoEntity dTO2Entity(CategoriaProductoDTO categoriaProductoDTO);

	List<CategoriaProductoDTO> toListDTO(Iterable<CategoriaProductoEntity> iterable);

}
