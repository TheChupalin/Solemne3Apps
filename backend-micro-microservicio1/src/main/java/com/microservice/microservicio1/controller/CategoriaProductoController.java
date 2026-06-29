package com.microservice.microservicio1.controller;

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

import com.microservice.microservicio1.dto.CategoriaProductoDTO;
import com.microservice.microservicio1.interfaces.ICategoriaProductoService;

@RestController
@RequestMapping("/api/microservicio1/CategoriaProducto")
public class CategoriaProductoController {
	
	private Logger logger = LoggerFactory.getLogger(CategoriaProductoController.class);

	@Autowired
	private ICategoriaProductoService categoriaProductoService;

	
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		logger.info("Micro1 CategoriaProductoController /api/microservicio1/CategoriaProducto/findAll");
		ResponseEntity<List<CategoriaProductoDTO>> categoriaProductoDTOListResponse;
		try
		{
			categoriaProductoDTOListResponse = categoriaProductoService.findAll();
			if (categoriaProductoDTOListResponse.getStatusCode() == HttpStatus.OK) {
				return categoriaProductoDTOListResponse;
			} else {
				String error = "HttpStatus=" + categoriaProductoDTOListResponse.getStatusCode() + " " + "categoriaProductoService.findAll";
				logger.error(new Object() {}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
			}		
		}
		catch (Exception e)
		{
			logger.error(new Object() {}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}		
	}

	
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable long id) {
		logger.info("Micro1 CategoriaProductoController /api/microservicio1/CategoriaProducto/findById/{id}");
		ResponseEntity<CategoriaProductoDTO> categoriaProductoDTOResponse;
		try {
			categoriaProductoDTOResponse = categoriaProductoService.findbyId(id);
			//check
			if (categoriaProductoDTOResponse.getStatusCode() == HttpStatus.OK || categoriaProductoDTOResponse.getStatusCode() == HttpStatus.NO_CONTENT) {
				return categoriaProductoDTOResponse;
			} else {
				String error = "HttpStatus=" + categoriaProductoDTOResponse.getStatusCode() + " " + "categoriaProductoService.findbyId id=" + id;
				logger.error(new Object() {}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
			}	
		} catch (Exception e) {
			logger.error(new Object() {}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody CategoriaProductoDTO categoriaProductoDTO) {
		logger.info("Micro1 CategoriaProductoController /api/microservicio1/CategoriaProducto/create");
		ResponseEntity<CategoriaProductoDTO> categoriaProductoDTOsavedResponse;
		try {
			categoriaProductoDTOsavedResponse = categoriaProductoService.create(categoriaProductoDTO);
			if (categoriaProductoDTOsavedResponse.getStatusCode() == HttpStatus.CREATED) {
				return categoriaProductoDTOsavedResponse;
			} else {
				String error = "HttpStatus=" + categoriaProductoDTOsavedResponse.getStatusCode() + " " + "categoriaProductoService.create";
				logger.error(new Object() {}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
			}				
		} catch (Exception e) {
			logger.error(new Object() {}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}		
	}

	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable long id,
			@RequestBody CategoriaProductoDTO categoriaProductoDTO) {
		logger.info("Micro1 CategoriaProductoController /api/microservicio1/CategoriaProducto/update/{id}");
		ResponseEntity<CategoriaProductoDTO> categoriaProductoDTOupdatedResponse;
		try {
			categoriaProductoDTOupdatedResponse = categoriaProductoService.update(id, categoriaProductoDTO);
			if (categoriaProductoDTOupdatedResponse.getStatusCode() == HttpStatus.OK) {
				return categoriaProductoDTOupdatedResponse;
			} else {
				String error = "HttpStatus=" + categoriaProductoDTOupdatedResponse.getStatusCode() + " " + "categoriaProductoService.update id=" + id;
				logger.error(new Object() {}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
			}	
		} catch (Exception e) {
			logger.error(new Object() {}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		logger.info("Micro1 CategoriaProductoController /api/microservicio1/CategoriaProducto/delete/{id}");
		ResponseEntity<CategoriaProductoDTO> categoriaProductoDTODeletedResponse;
		try {
			categoriaProductoDTODeletedResponse = categoriaProductoService.deleteById(id);
			if (categoriaProductoDTODeletedResponse.getStatusCode() == HttpStatus.OK) {
				return categoriaProductoDTODeletedResponse;
			} else {
				String error = "HttpStatus=" + categoriaProductoDTODeletedResponse.getStatusCode() + " " + "categoriaProductoService.deleteById id=" + id;
				logger.error(new Object() {}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
			}
		} catch (Exception e) {
			logger.error(new Object() {}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}			
	}

}
