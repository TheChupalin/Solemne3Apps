package com.microservice.bff.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.bff.dto.CategoriaProductoDTO;

public interface IBffCategoriaProductoService {

	ResponseEntity<List<CategoriaProductoDTO>> findAll() throws Exception;

	ResponseEntity<CategoriaProductoDTO> findById(@PathVariable Long id) throws Exception;

	ResponseEntity<CategoriaProductoDTO> create(@RequestBody CategoriaProductoDTO categoriaProductoDTO)
			throws Exception;

	ResponseEntity<CategoriaProductoDTO> update(@PathVariable Long id,
			@RequestBody CategoriaProductoDTO categoriaProductoDTO) throws Exception;

	ResponseEntity<CategoriaProductoDTO> deleteById(@PathVariable Long id) throws Exception;

}
