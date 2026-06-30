package com.microservice.bff.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.bff.client.IBffMicroServicio2Client;
import com.microservice.bff.dto.IndicadoresActualesDTO;
import com.microservice.bff.dto.MindicadorAllDTO;
import com.microservice.bff.dto.MindicadorUfDTO;
import com.microservice.bff.dto.ProductoDTO;
import com.microservice.bff.dto.ProductoPrecioDolarDTO;
import com.microservice.bff.dto.ProductoPrecioUfDTO;
import com.microservice.bff.interfaces.IBffIndicadorService;

@Service
public class BffIndicadorServiceImpl implements IBffIndicadorService {

	private static final String MINDICADOR_URL = "https://mindicador.cl/api";

	private Logger logger = LoggerFactory.getLogger(BffIndicadorServiceImpl.class);

	@Autowired
	private IBffMicroServicio2Client microServicio2Client;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<ProductoPrecioUfDTO> getPrecioProductoEnUf(Long idProducto) throws Exception {
		logger.info("BFF BffIndicadorServiceImpl getPrecioProductoEnUf() idProducto: " + idProducto);
		try {
			// 1. Obtener el producto desde microservicio2
			ResponseEntity<ProductoDTO> productoResponse = microServicio2Client.findById(idProducto);
			if (productoResponse.getStatusCode() == HttpStatus.NO_CONTENT || productoResponse.getBody() == null) {
				String error = "Producto con id=" + idProducto + " no encontrado";
				logger.error(new Object() {}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
			}
			ProductoDTO producto = productoResponse.getBody();

			// 2. Obtener el valor actual de la UF desde Mindicador
			MindicadorUfDTO mindicadorResponse = restTemplate.getForObject(MINDICADOR_URL, MindicadorUfDTO.class);
			if (mindicadorResponse == null || mindicadorResponse.getUf() == null) {
				String error = "No se pudo obtener el valor de la UF desde Mindicador";
				logger.error(new Object() {}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
			}

			// 3. Calcular el precio en UF (precio CLP / valor UF), redondeado a 4 decimales
			double valorUf = mindicadorResponse.getUf().getValor();
			double precioUf = Math.round((producto.getPrecio() / valorUf) * 10000.0) / 10000.0;

			// 4. Construir la respuesta
			ProductoPrecioUfDTO resultado = ProductoPrecioUfDTO.builder()
					.idProducto(producto.getId())
					.nombreProducto(producto.getNombre())
					.precioClp(producto.getPrecio())
					.precioUf(precioUf)
					.valorUfActual(valorUf)
					.fechaUf(mindicadorResponse.getUf().getFecha())
					.build();

			return ResponseEntity.status(HttpStatus.OK).body(resultado);

		} catch (Exception e) {
			logger.error(new Object() {}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<ProductoPrecioDolarDTO> getPrecioProductoEnDolar(Long idProducto) throws Exception {
		logger.info("BFF BffIndicadorServiceImpl getPrecioProductoEnDolar() idProducto: " + idProducto);
		try {
			// 1. Obtener el producto desde microservicio2
			ResponseEntity<ProductoDTO> productoResponse = microServicio2Client.findById(idProducto);
			if (productoResponse.getStatusCode() == HttpStatus.NO_CONTENT || productoResponse.getBody() == null) {
				String error = "Producto con id=" + idProducto + " no encontrado";
				logger.error(new Object() {}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
			}
			ProductoDTO producto = productoResponse.getBody();

			// 2. Obtener el valor actual del Dólar desde Mindicador
			MindicadorAllDTO mindicadorResponse = restTemplate.getForObject(MINDICADOR_URL, MindicadorAllDTO.class);
			if (mindicadorResponse == null || mindicadorResponse.getDolar() == null) {
				String error = "No se pudo obtener el valor del Dólar desde Mindicador";
				logger.error(new Object() {}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
			}

			// 3. Calcular el precio en Dólar (precio CLP / valor Dólar), redondeado a 2 decimales
			double valorDolar = mindicadorResponse.getDolar().getValor();
			double precioDolar = Math.round((producto.getPrecio() / valorDolar) * 100.0) / 100.0;

			// 4. Construir la respuesta
			ProductoPrecioDolarDTO resultado = ProductoPrecioDolarDTO.builder()
					.idProducto(producto.getId())
					.nombreProducto(producto.getNombre())
					.precioClp(producto.getPrecio())
					.precioDolar(precioDolar)
					.valorDolarActual(valorDolar)
					.fechaDolar(mindicadorResponse.getDolar().getFecha())
					.build();

			return ResponseEntity.status(HttpStatus.OK).body(resultado);

		} catch (Exception e) {
			logger.error(new Object() {}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<IndicadoresActualesDTO> getIndicadoresActuales() throws Exception {
		logger.info("BFF BffIndicadorServiceImpl getIndicadoresActuales()");
		try {
			// Obtener todos los indicadores desde Mindicador
			MindicadorAllDTO mindicadorResponse = restTemplate.getForObject(MINDICADOR_URL, MindicadorAllDTO.class);
			if (mindicadorResponse == null) {
				String error = "No se pudo obtener los indicadores desde Mindicador";
				logger.error(new Object() {}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
			}

			// Construir la respuesta mapeando cada indicador
			IndicadoresActualesDTO resultado = IndicadoresActualesDTO.builder()
					.uf(mapIndicador(mindicadorResponse.getUf()))
					.dolar(mapIndicador(mindicadorResponse.getDolar()))
					.euro(mapIndicador(mindicadorResponse.getEuro()))
					.utm(mapIndicador(mindicadorResponse.getUtm()))
					.ipc(mapIndicador(mindicadorResponse.getIpc()))
					.tpm(mapIndicador(mindicadorResponse.getTpm()))
					.imacec(mapIndicador(mindicadorResponse.getImacec()))
					.tasaDesempleo(mapIndicador(mindicadorResponse.getTasaDesempleo()))
					.bitcoin(mapIndicador(mindicadorResponse.getBitcoin()))
					.libraCobre(mapIndicador(mindicadorResponse.getLibraCobre()))
					.build();

			return ResponseEntity.status(HttpStatus.OK).body(resultado);

		} catch (Exception e) {
			logger.error(new Object() {}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * Mapea un IndicadorDTO de MindicadorAllDTO a IndicadorItemDTO de IndicadoresActualesDTO.
	 * Retorna null de forma segura si el origen es null.
	 */
	private IndicadoresActualesDTO.IndicadorItemDTO mapIndicador(MindicadorAllDTO.IndicadorDTO origen) {
		if (origen == null) return null;
		return IndicadoresActualesDTO.IndicadorItemDTO.builder()
				.codigo(origen.getCodigo())
				.nombre(origen.getNombre())
				.unidadMedida(origen.getUnidadMedida())
				.valor(origen.getValor())
				.fecha(origen.getFecha())
				.build();
	}

}

