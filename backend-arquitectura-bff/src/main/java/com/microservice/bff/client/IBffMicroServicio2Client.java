package com.microservice.bff.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.microservice.bff.dto.ProductoDTO;

@FeignClient(name = "microservicio2")
public interface IBffMicroServicio2Client {

	String PATH = "/api/microservicio2/Producto";

	@GetMapping(PATH + "/findAll")
	ResponseEntity<List<ProductoDTO>> findAll();

	@GetMapping(PATH + "/findById/{id}")
	ResponseEntity<ProductoDTO> findById(@PathVariable long id);

	@PostMapping(PATH + "/create")
	ResponseEntity<ProductoDTO> create(@RequestBody ProductoDTO productoDTO);

	@PutMapping(PATH + "/update/{id}")
	ResponseEntity<ProductoDTO> update(@PathVariable Long id, @RequestBody ProductoDTO productoDTO);

	@DeleteMapping(PATH + "/delete/{id}")
	ResponseEntity<ProductoDTO> deleteById(@PathVariable Long id);
}
