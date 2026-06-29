package com.microservice.microservicio2.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.microservice.microservicio2.dto.ProductoDTO;

public interface IProductoService {

	ResponseEntity<List<ProductoDTO>> findAll() throws Exception;

	ResponseEntity<ProductoDTO> findbyId(long id) throws Exception;

	ResponseEntity<ProductoDTO> create(ProductoDTO categoriaProductoDTO) throws Exception;

	ResponseEntity<ProductoDTO> update(long id, ProductoDTO categoriaProductoDTO) throws Exception;

	ResponseEntity<ProductoDTO> deleteById(long id) throws Exception;
	
}