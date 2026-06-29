package com.microservice.bff.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import com.microservice.bff.dto.ProductoDTO;

public interface IBffProductoService {

	ResponseEntity<List<ProductoDTO>> findAll() throws Exception;

	ResponseEntity<ProductoDTO> findById(@PathVariable Long id) throws Exception;

	ResponseEntity<ProductoDTO> create(@RequestBody ProductoDTO productoDTO) throws Exception;

	ResponseEntity<ProductoDTO> update(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) throws Exception;

	ResponseEntity<ProductoDTO> deleteById(@PathVariable Long id) throws Exception;

}
