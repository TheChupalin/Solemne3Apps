package com.microservice.bff.service;

import com.microservice.bff.client.IBffMicroServicio1Client;
import com.microservice.bff.dto.CategoriaProductoDTO;
import com.microservice.bff.interfaces.IBffCategoriaProductoService;

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
public class BffCategoriaProductoServiceImpl implements IBffCategoriaProductoService {

	private Logger logger = LoggerFactory.getLogger(BffCategoriaProductoServiceImpl.class);

	@Autowired
	private IBffMicroServicio1Client microServicio1Client;

	@Override
	public ResponseEntity<List<CategoriaProductoDTO>> findAll() throws Exception {
		logger.info("BFF CategoriaProductoServiceImpl findAll()");
		try {
			ResponseEntity<List<CategoriaProductoDTO>> categoriaProductoDTOListResponse = microServicio1Client
					.findAll();
			if (categoriaProductoDTOListResponse.getStatusCode() == HttpStatus.OK) {
				return categoriaProductoDTOListResponse;
			} else {
				String error = "HttpStatus=" + categoriaProductoDTOListResponse.getStatusCode() + " "
						+ "microServicio1Client.findAll";
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
	public ResponseEntity<CategoriaProductoDTO> findById(@PathVariable Long id) throws Exception {
		logger.info("BFF CategoriaProductoServiceImpl findById() id: " + id);
		try {
			return microServicio1Client.findById(id);
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<CategoriaProductoDTO> create(@RequestBody CategoriaProductoDTO categoriaProductoDTO)
			throws Exception {
		logger.info("BFF CategoriaProductoServiceImpl create() categoriaProductoDTO: " + categoriaProductoDTO);
		try {
			return microServicio1Client.create(categoriaProductoDTO);
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<CategoriaProductoDTO> update(@PathVariable Long id,
			@RequestBody CategoriaProductoDTO categoriaProductoDTO) throws Exception {
		logger.info("BFF CategoriaProductoServiceImpl update() id: " + id + " categoriaProductoDTO: "
				+ categoriaProductoDTO);
		try {
			return microServicio1Client.update(id, categoriaProductoDTO);
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<CategoriaProductoDTO> deleteById(@PathVariable Long id) throws Exception {
		logger.info("BFF CategoriaProductoServiceImpl deleteById() id: " + id);
		try {
			return microServicio1Client.deleteById(id);
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

}
