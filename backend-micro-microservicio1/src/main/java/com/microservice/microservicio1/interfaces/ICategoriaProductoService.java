package com.microservice.microservicio1.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.microservice.microservicio1.dto.CategoriaProductoDTO;

public interface ICategoriaProductoService {

	ResponseEntity<List<CategoriaProductoDTO>> findAll() throws Exception;

	ResponseEntity<CategoriaProductoDTO> findbyId(long id) throws Exception;

	ResponseEntity<CategoriaProductoDTO> create(CategoriaProductoDTO categoriaProductoDTO) throws Exception;

	ResponseEntity<CategoriaProductoDTO> update(long id, CategoriaProductoDTO categoriaProductoDTO) throws Exception;

	ResponseEntity<CategoriaProductoDTO> deleteById(long id) throws Exception;
	
}