package com.microservice.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoPrecioUfDTO {

	private Long idProducto;
	private String nombreProducto;
	private Double precioClp;
	private Double precioUf;
	private Double valorUfActual;
	private String fechaUf;

}
