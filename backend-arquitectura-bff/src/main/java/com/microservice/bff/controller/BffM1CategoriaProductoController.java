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

import com.microservice.bff.dto.CategoriaProductoDTO;
import com.microservice.bff.interfaces.IBffCategoriaProductoService;

@RestController
@RequestMapping("/api/bff/microservicio1/CategoriaProducto")
public class BffM1CategoriaProductoController {

	private Logger logger = LoggerFactory.getLogger(BffM1CategoriaProductoController.class);

	@Autowired
	private IBffCategoriaProductoService categoriaProductoService;

	@GetMapping("/findAll")
	public ResponseEntity<List<CategoriaProductoDTO>> findAll() {
		logger.info("BffM1CategoriaProductoController /api/bff/microservicio1/CategoriaProducto/findAll");

		ResponseEntity<List<CategoriaProductoDTO>> categoriaProductoDTOListResponse = null;
		try {
			categoriaProductoDTOListResponse = categoriaProductoService.findAll();
			if (categoriaProductoDTOListResponse.getStatusCode() == HttpStatus.OK) {
				return categoriaProductoDTOListResponse;
			} else {
				String error = "HttpStatus=" + categoriaProductoDTOListResponse.getStatusCode() + " "
						+ "categoriaProductoService.findAll";
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<CategoriaProductoDTO> findById(@PathVariable Long id) throws Exception {
		logger.info("BffM1CategoriaProductoController /api/bff/microservicio1/CategoriaProducto/findById/{id}");

		ResponseEntity<CategoriaProductoDTO> categoriaProductoDTOResponse = null;
		try {
			categoriaProductoDTOResponse = categoriaProductoService.findById(id);
			if (categoriaProductoDTOResponse.getStatusCode() == HttpStatus.OK) {
				return categoriaProductoDTOResponse;
			} else {
				String error = "HttpStatus=" + categoriaProductoDTOResponse.getStatusCode() + " "
						+ "categoriaProductoService.findById";
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<CategoriaProductoDTO> create(@RequestBody CategoriaProductoDTO categoriaProductoDTO)
			throws Exception {
		logger.info("BffM1CategoriaProductoController /api/bff/microservicio1/CategoriaProducto/create");

		ResponseEntity<CategoriaProductoDTO> categoriaProductoDTOSavedResponse = null;
		try {
			categoriaProductoDTOSavedResponse = categoriaProductoService.create(categoriaProductoDTO);
			if (categoriaProductoDTOSavedResponse.getStatusCode() == HttpStatus.CREATED) {
				return categoriaProductoDTOSavedResponse;
			} else {
				String error = "HttpStatus=" + categoriaProductoDTOSavedResponse.getStatusCode() + " "
						+ "categoriaProductoService.create";
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CategoriaProductoDTO> update(@PathVariable Long id,
			@RequestBody CategoriaProductoDTO categoriaProductoDTO) throws Exception {
		logger.info("BffM1CategoriaProductoController /api/bff/microservicio1/CategoriaProducto/update/{id}");

		ResponseEntity<CategoriaProductoDTO> categoriaProductoDTOUpdatedResponse = null;
		try {
			categoriaProductoDTOUpdatedResponse = categoriaProductoService.update(id, categoriaProductoDTO);
			if (categoriaProductoDTOUpdatedResponse.getStatusCode() == HttpStatus.OK) {
				return categoriaProductoDTOUpdatedResponse;
			} else {
				String error = "HttpStatus=" + categoriaProductoDTOUpdatedResponse.getStatusCode() + " "
						+ "categoriaProductoService.update";
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CategoriaProductoDTO> delete(@PathVariable Long id) throws Exception {
		logger.info("BffM1CategoriaProductoController /api/bff/microservicio1/CategoriaProducto/delete/{id}");

		ResponseEntity<CategoriaProductoDTO> categoriaProductoDTODeletedResponse = null;
		try {
			categoriaProductoDTODeletedResponse = categoriaProductoService.deleteById(id);
			if (categoriaProductoDTODeletedResponse.getStatusCode() == HttpStatus.OK) {
				return categoriaProductoDTODeletedResponse;
			} else {
				String error = "HttpStatus=" + categoriaProductoDTODeletedResponse.getStatusCode() + " "
						+ "categoriaProductoService.delete";
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
