package com.microservice.bff.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.bff.dto.ProductoDTO;
import com.microservice.bff.interfaces.IBffProductoService;

@RestController
@RequestMapping("/api/bff/microservicio2/Producto")
public class BffM2ProductoController {

	private Logger logger = LoggerFactory.getLogger(BffM2ProductoController.class);

	@Autowired
	private IBffProductoService productoService;

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		logger.info("BffM2ProductoController /api/bff/microservicio2/Producto/findAll");
		ResponseEntity<List<ProductoDTO>> productoDTOListResponse = null;
		try {
			productoDTOListResponse = productoService.findAll();
			if (productoDTOListResponse.getStatusCode() == HttpStatus.OK) {
				return productoDTOListResponse;
			} else {
				String error = "HttpStatus=" + productoDTOListResponse.getStatusCode() + " "
						+ "productoService.findAll";
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
			}
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		logger.info("BffM2ProductoController /api/bff/microservicio2/Producto/findById/{id}");
		ResponseEntity<ProductoDTO> productoDTOResponse;
		try {
			productoDTOResponse = productoService.findById(id);
			if (productoDTOResponse.getStatusCode() == HttpStatus.OK) {
				return productoDTOResponse;
			} else {
				String error = "HttpStatus=" + productoDTOResponse.getStatusCode() + " " + "productoService.findById";
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
			}
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody ProductoDTO productoDTO) {
		logger.info("BffM2ProductoController /api/bff/microservicio2/Producto/create");
		ResponseEntity<ProductoDTO> productoDTOResponse;
		try {
			productoDTOResponse = productoService.create(productoDTO);
			if (productoDTOResponse.getStatusCode() == HttpStatus.CREATED) {
				return productoDTOResponse;
			} else {
				String error = "HttpStatus=" + productoDTOResponse.getStatusCode() + " " + "productoService.create";
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
			}
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			if (e.getMessage() != null && e.getMessage().contains("no existe")) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
		logger.info("BffM2ProductoController /api/bff/microservicio2/Producto/update/{id}");
		ResponseEntity<ProductoDTO> productoDTOResponse;
		try {
			productoDTOResponse = productoService.update(id, productoDTO);
			if (productoDTOResponse.getStatusCode() == HttpStatus.OK) {
				return productoDTOResponse;
			} else {
				String error = "HttpStatus=" + productoDTOResponse.getStatusCode() + " " + "productoService.update";
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
			}
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			if (e.getMessage() != null && e.getMessage().contains("no encontrado")) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		logger.info("BffM2ProductoController /api/bff/microservicio2/Producto/delete/{id}");
		ResponseEntity<ProductoDTO> productoDTOResponse;
		try {
			productoDTOResponse = productoService.deleteById(id);
			if (productoDTOResponse.getStatusCode() == HttpStatus.OK) {
				return productoDTOResponse;
			} else {
				String error = "HttpStatus=" + productoDTOResponse.getStatusCode() + " " + "productoService.delete";
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
			}
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			if (e.getMessage() != null && e.getMessage().contains("no encontrado")) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
