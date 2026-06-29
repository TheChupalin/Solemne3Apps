package com.microservice.bff.service;

import com.microservice.bff.client.IBffMicroServicio1Client;
import com.microservice.bff.client.IBffMicroServicio2Client;
import com.microservice.bff.dto.CategoriaProductoDTO;
import com.microservice.bff.dto.ProductoDTO;
import com.microservice.bff.interfaces.IBffProductoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BffProductoServiceImpl implements IBffProductoService {

	private Logger logger = LoggerFactory.getLogger(BffProductoServiceImpl.class);

	@Autowired
	private IBffMicroServicio2Client microServicio2Client;

	@Autowired
	private IBffMicroServicio1Client microServicio1Client;

	@Override
	public ResponseEntity<List<ProductoDTO>> findAll() throws Exception {
		logger.info("BFF ProductoServiceImpl findAll()");
		try {
			ResponseEntity<List<ProductoDTO>> productoDTOListResponse = microServicio2Client.findAll();
			if (productoDTOListResponse.getStatusCode() == HttpStatus.OK) {
				return productoDTOListResponse;
			} else {
				String error = "HttpStatus=" + productoDTOListResponse.getStatusCode() + " "
						+ "microServicio2Client.findAll";
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
			}
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<ProductoDTO> findById(@PathVariable Long id) throws Exception {
		logger.info("BFF ProductoServiceImpl findById() id: " + id);
		try {
			return microServicio2Client.findById(id);
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<ProductoDTO> create(@RequestBody ProductoDTO productoDTO) throws Exception {
		logger.info("BFF ProductoServiceImpl create");
		ResponseEntity<ProductoDTO> productoDTOSavedResponse;
		try {
			if (productoDTO.getIdCategoria() == null) {
				String error = "El campo idCategoria es obligatorio";
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
			}

			ResponseEntity<CategoriaProductoDTO> categoriaResponse = microServicio1Client
					.findById(productoDTO.getIdCategoria());
			if (categoriaResponse.getStatusCode() == HttpStatus.NO_CONTENT
					|| categoriaResponse.getBody() == null) {
				String error = "La categoria con id=" + productoDTO.getIdCategoria() + " no existe";
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
			}

			productoDTOSavedResponse = microServicio2Client.create(productoDTO);
			if (productoDTOSavedResponse.getStatusCode() == HttpStatus.CREATED) {
				return productoDTOSavedResponse;
			} else {
				String error = "HttpStatus=" + productoDTOSavedResponse.getStatusCode() + " "
						+ "microServicio2Client.create";
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
			}
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<ProductoDTO> update(@PathVariable Long id, @RequestBody ProductoDTO productoDTO)
			throws Exception {
		logger.info("BFF ProductoServiceImpl update() id: " + id + " productoDTO: " + productoDTO);
		try {
			return microServicio2Client.update(id, productoDTO);
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<ProductoDTO> deleteById(@PathVariable Long id) throws Exception {
		logger.info("BFF ProductoServiceImpl deleteById() id: " + id);
		try {
			return microServicio2Client.deleteById(id);
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

}
