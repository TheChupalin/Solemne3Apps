package com.microservice.microservicio2.controller;

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

import com.microservice.microservicio2.dto.ProductoDTO;
import com.microservice.microservicio2.interfaces.IProductoService;

@RestController
@RequestMapping("/api/microservicio2/Producto")
public class ProductoController {

	private Logger logger = LoggerFactory.getLogger(ProductoController.class);

	@Autowired
	private IProductoService productoService;

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		logger.info("Micro2 ProductoController /api/microservicio2/Producto/findAll");
		ResponseEntity<List<ProductoDTO>> productoDTOListResponse;
		try {
			productoDTOListResponse = productoService.findAll();
			if (productoDTOListResponse.getStatusCode() == HttpStatus.OK) {
				return productoDTOListResponse;
			} else {
				String error = "HttpStatus=" + productoDTOListResponse.getStatusCode() + " "
						+ "productoService.findAll";
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
			}
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable long id) {
		logger.info("Micro2 ProductoController /api/microservicio2/Producto/findById/{id}");
		ResponseEntity<ProductoDTO> productoDTOResponse;
		try {
			productoDTOResponse = productoService.findbyId(id);
			// check
			if (productoDTOResponse.getStatusCode() == HttpStatus.OK
					|| productoDTOResponse.getStatusCode() == HttpStatus.NO_CONTENT) {
				return productoDTOResponse;
			} else {
				String error = "HttpStatus=" + productoDTOResponse.getStatusCode() + " "
						+ "productoService.findbyId id=" + id;
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
			}
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody ProductoDTO productoDTO) {
		logger.info("Micro2 ProductoController /api/microservicio2/Producto/create");
		ResponseEntity<ProductoDTO> productoDTOsavedResponse;
		try {
			productoDTOsavedResponse = productoService.create(productoDTO);
			if (productoDTOsavedResponse.getStatusCode() == HttpStatus.CREATED) {
				return productoDTOsavedResponse;
			} else {
				String error = "HttpStatus=" + productoDTOsavedResponse.getStatusCode() + " "
						+ "productoService.create";
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
			}
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable long id,
			@RequestBody ProductoDTO productoDTO) {
		logger.info("Micro2 ProductoController /api/microservicio2/Producto/update/{id}");
		ResponseEntity<ProductoDTO> productoDTOupdatedResponse;
		try {
			productoDTOupdatedResponse = productoService.update(id, productoDTO);
			if (productoDTOupdatedResponse.getStatusCode() == HttpStatus.OK) {
				return productoDTOupdatedResponse;
			} else {
				String error = "HttpStatus=" + productoDTOupdatedResponse.getStatusCode() + " "
						+ "productoService.update id=" + id;
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
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
		logger.info("Micro2 ProductoController /api/microservicio2/Producto/delete/{id}");
		ResponseEntity<ProductoDTO> productoDTODeletedResponse;
		try {
			productoDTODeletedResponse = productoService.deleteById(id);
			if (productoDTODeletedResponse.getStatusCode() == HttpStatus.OK) {
				return productoDTODeletedResponse;
			} else {
				String error = "HttpStatus=" + productoDTODeletedResponse.getStatusCode() + " "
						+ "productoService.deleteById id=" + id;
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
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
