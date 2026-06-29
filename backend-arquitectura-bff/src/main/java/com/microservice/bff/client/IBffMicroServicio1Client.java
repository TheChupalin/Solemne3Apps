package com.microservice.bff.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.bff.dto.CategoriaProductoDTO;

@FeignClient(name = "microservicio1")
public interface IBffMicroServicio1Client {
	
	String PATH = "/api/microservicio1/CategoriaProducto";

	@GetMapping(PATH + "/findAll")
	ResponseEntity<List<CategoriaProductoDTO>> findAll();
	
	@GetMapping(PATH + "/findById/{id}")
	ResponseEntity<CategoriaProductoDTO> findById(@PathVariable long id);
	
	@PostMapping(PATH + "/create")
	ResponseEntity<CategoriaProductoDTO> create(@RequestBody CategoriaProductoDTO categoriaProductoDTO);
	
	@PutMapping(PATH + "/update/{id}")
	ResponseEntity<CategoriaProductoDTO> update(@PathVariable long id,	@RequestBody CategoriaProductoDTO categoriaProductoDTO);
	
	@DeleteMapping(PATH + "/delete/{id}")
	ResponseEntity<CategoriaProductoDTO> deleteById(@PathVariable Long id);
}