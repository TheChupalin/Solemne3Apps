package com.microservice.bff.interfaces;

import org.springframework.http.ResponseEntity;

import com.microservice.bff.dto.ProductoPrecioUfDTO;

public interface IBffIndicadorService {

	ResponseEntity<ProductoPrecioUfDTO> getPrecioProductoEnUf(Long idProducto) throws Exception;

}
