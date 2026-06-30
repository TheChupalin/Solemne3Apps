package com.microservice.bff.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MindicadorAllDTO {

	@JsonProperty("uf")
	private IndicadorDTO uf;

	@JsonProperty("dolar")
	private IndicadorDTO dolar;

	@JsonProperty("euro")
	private IndicadorDTO euro;

	@JsonProperty("utm")
	private IndicadorDTO utm;

	@JsonProperty("ipc")
	private IndicadorDTO ipc;

	@JsonProperty("tpm")
	private IndicadorDTO tpm;

	@JsonProperty("imacec")
	private IndicadorDTO imacec;

	@JsonProperty("tasa_desempleo")
	private IndicadorDTO tasaDesempleo;

	@JsonProperty("bitcoin")
	private IndicadorDTO bitcoin;

	@JsonProperty("libra_cobre")
	private IndicadorDTO libraCobre;

	@Data
	@NoArgsConstructor
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class IndicadorDTO {
		private String codigo;
		private String nombre;
		@JsonProperty("unidad_medida")
		private String unidadMedida;
		private Double valor;
		private String fecha;
	}

}
