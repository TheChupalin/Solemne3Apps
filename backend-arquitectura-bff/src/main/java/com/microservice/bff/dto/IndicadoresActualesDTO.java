package com.microservice.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IndicadoresActualesDTO {

	private IndicadorItemDTO uf;
	private IndicadorItemDTO dolar;
	private IndicadorItemDTO euro;
	private IndicadorItemDTO utm;
	private IndicadorItemDTO ipc;
	private IndicadorItemDTO tpm;
	private IndicadorItemDTO imacec;
	private IndicadorItemDTO tasaDesempleo;
	private IndicadorItemDTO bitcoin;
	private IndicadorItemDTO libraCobre;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class IndicadorItemDTO {
		private String codigo;
		private String nombre;
		private String unidadMedida;
		private Double valor;
		private String fecha;
	}

}
