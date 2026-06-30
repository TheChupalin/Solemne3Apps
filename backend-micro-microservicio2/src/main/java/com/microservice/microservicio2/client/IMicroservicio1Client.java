package com.microservice.microservicio2.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.microservicio2.dto.CategoriaProductoDTO;

@FeignClient(name = "microservicio1")
public interface IMicroservicio1Client {

	String PATH = "/api/microservicio1/CategoriaProducto";

	@GetMapping(PATH + "/findById/{id}")
	ResponseEntity<CategoriaProductoDTO> findCategoriaById(@PathVariable long id);

}
