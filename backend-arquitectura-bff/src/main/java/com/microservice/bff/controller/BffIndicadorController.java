package com.microservice.bff.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.bff.dto.ProductoPrecioUfDTO;
import com.microservice.bff.interfaces.IBffIndicadorService;

@RestController
@RequestMapping("/api/bff/indicadores")
public class BffIndicadorController {

	private Logger logger = LoggerFactory.getLogger(BffIndicadorController.class);

	@Autowired
	private IBffIndicadorService indicadorService;

	@GetMapping("/producto/{id}/precio-uf")
	public ResponseEntity<?> getPrecioProductoEnUf(@PathVariable Long id) {
		logger.info("BffIndicadorController GET /api/bff/indicadores/producto/{id}/precio-uf id=" + id);
		try {
			ResponseEntity<ProductoPrecioUfDTO> response = indicadorService.getPrecioProductoEnUf(id);
			if (response.getStatusCode() == HttpStatus.OK) {
				return response;
			} else {
				String error = "HttpStatus=" + response.getStatusCode() + " indicadorService.getPrecioProductoEnUf";
				logger.error(new Object() {}.getClass().getEnclosingMethod().getName() + " " + error);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
			}
		} catch (Exception e) {
			logger.error(new Object() {}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			if (e.getMessage() != null && e.getMessage().contains("no encontrado")) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
