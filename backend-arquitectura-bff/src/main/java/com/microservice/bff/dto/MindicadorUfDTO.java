package com.microservice.bff.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MindicadorUfDTO {

	@JsonProperty("uf")
	private IndicadorDTO uf;

	@Data
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class IndicadorDTO {
		private String codigo;
		private String nombre;
		private Double valor;
		private String fecha;
	}

}
