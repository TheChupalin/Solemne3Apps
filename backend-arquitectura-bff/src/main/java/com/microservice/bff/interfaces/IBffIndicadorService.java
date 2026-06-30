package com.microservice.bff.interfaces;

import org.springframework.http.ResponseEntity;

import com.microservice.bff.dto.IndicadoresActualesDTO;
import com.microservice.bff.dto.ProductoPrecioDolarDTO;
import com.microservice.bff.dto.ProductoPrecioUfDTO;

public interface IBffIndicadorService {

	ResponseEntity<ProductoPrecioUfDTO> getPrecioProductoEnUf(Long idProducto) throws Exception;

	ResponseEntity<ProductoPrecioDolarDTO> getPrecioProductoEnDolar(Long idProducto) throws Exception;

	ResponseEntity<IndicadoresActualesDTO> getIndicadoresActuales() throws Exception;

}
