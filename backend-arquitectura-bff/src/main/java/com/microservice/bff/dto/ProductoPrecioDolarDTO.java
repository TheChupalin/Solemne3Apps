package com.microservice.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoPrecioDolarDTO {

	private Long idProducto;
	private String nombreProducto;
	private Double precioClp;
	private Double precioDolar;
	private Double valorDolarActual;
	private String fechaDolar;

}
