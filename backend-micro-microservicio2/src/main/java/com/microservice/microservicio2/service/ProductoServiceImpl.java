package com.microservice.microservicio2.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservice.microservicio2.client.IMicroservicio1Client;
import com.microservice.microservicio2.dto.CategoriaProductoDTO;
import com.microservice.microservicio2.dto.ProductoDTO;
import com.microservice.microservicio2.entity.ProductoEntity;
import com.microservice.microservicio2.interfaces.IProductoMapper;
import com.microservice.microservicio2.interfaces.IProductoService;
import com.microservice.microservicio2.repository.IProductoRepository;

@Service
public class ProductoServiceImpl implements IProductoService {

	private Logger logger = LoggerFactory.getLogger(ProductoServiceImpl.class);

	@Autowired
	private IProductoRepository productoRepository;

	@Autowired
	private IProductoMapper productoMapper;

	@Autowired
	private IMicroservicio1Client microservicio1Client;

	@Override
	public ResponseEntity<List<ProductoDTO>> findAll() throws Exception {
		logger.info("Micro2 ProductoServiceImpl findAll");
		List<ProductoEntity> productoEntityList;
		List<ProductoDTO> productoDTOList = null;
		try {
			productoEntityList = productoRepository.findAll();
			productoDTOList = productoMapper.toListDTO(productoEntityList);
			return ResponseEntity.status(HttpStatus.OK).body(productoDTOList);
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<ProductoDTO> findbyId(long id) throws Exception {
		logger.info("Micro2 ProductoServiceImpl findbyId");
		Optional<ProductoEntity> optionalProductoEntity;
		ProductoEntity productoEntity;
		ProductoDTO productoDTO = null;
		try {
			optionalProductoEntity = productoRepository.findById(id);
			productoEntity = optionalProductoEntity.orElse(null);
			if (productoEntity != null) {
				productoDTO = productoMapper.entity2DTO(productoEntity);
				return ResponseEntity.status(HttpStatus.OK).body(productoDTO);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			}
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<ProductoDTO> create(ProductoDTO productoDTO) throws Exception {
		logger.info("Micro2 ProductoServiceImpl create");
		ProductoEntity productoEntity2Save;
		ProductoEntity productoEntitySaved;
		ProductoDTO productoDTOSaved = null;
		try {
			// Validar que idCategoria no sea null
			if (productoDTO.getIdCategoria() == null) {
				String error = "El campo idCategoria es obligatorio";
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
			}

			// Validar que la categoría exista en microservicio1
			ResponseEntity<CategoriaProductoDTO> categoriaResponse = microservicio1Client
					.findCategoriaById(productoDTO.getIdCategoria());
			if (categoriaResponse.getStatusCode() == HttpStatus.NO_CONTENT
					|| categoriaResponse.getBody() == null) {
				String error = "La categoria con id=" + productoDTO.getIdCategoria() + " no existe";
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
			}

			productoEntity2Save = productoMapper.dTO2Entity(productoDTO);
			productoEntitySaved = productoRepository.save(productoEntity2Save);
			productoDTOSaved = productoMapper.entity2DTO(productoEntitySaved);
			return ResponseEntity.status(HttpStatus.CREATED).body(productoDTOSaved);
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<ProductoDTO> update(long id, ProductoDTO productoDTO) throws Exception {
		logger.info("Micro2 ProductoServiceImpl update");
		Optional<ProductoEntity> optionalProductoEntity;
		ProductoEntity productoEntity;
		ProductoEntity productoEntity2Save;
		ProductoEntity productoEntitySaved;
		ProductoDTO productoDTOSaved;
		try {
			// Validar que el producto exista
			optionalProductoEntity = productoRepository.findById(id);
			productoEntity = optionalProductoEntity.orElse(null);
			if (productoEntity == null) {
				String error = "productoRepository.findById id=" + id + " no encontrado";
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
			}

			// Validar que idCategoria no sea null
			if (productoDTO.getIdCategoria() == null) {
				String error = "El campo idCategoria es obligatorio";
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
			}

			// Validar que la categoría exista en microservicio1
			ResponseEntity<CategoriaProductoDTO> categoriaResponse = microservicio1Client
					.findCategoriaById(productoDTO.getIdCategoria());
			if (categoriaResponse.getStatusCode() == HttpStatus.NO_CONTENT
					|| categoriaResponse.getBody() == null) {
				String error = "La categoria con id=" + productoDTO.getIdCategoria() + " no existe";
				logger.error(new Object() {
				}.getClass().getEnclosingMethod().getName() + " " + error);
				throw new Exception(error);
			}

			productoEntity2Save = productoMapper.dTO2Entity(productoDTO);
			productoEntity2Save.setId(id);
			productoEntitySaved = productoRepository.save(productoEntity2Save);
			productoDTOSaved = productoMapper.entity2DTO(productoEntitySaved);
			return ResponseEntity.status(HttpStatus.OK).body(productoDTOSaved);
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<ProductoDTO> deleteById(long id) throws Exception {
		logger.info("Micro2 ProductoServiceImpl deleteById");
		Optional<ProductoEntity> optionalProductoEntity;
		ProductoEntity productoEntity;
		ProductoDTO productoCategoriaDeleted;
		try {
			optionalProductoEntity = productoRepository.findById(id);
			productoEntity = optionalProductoEntity.orElse(null);
			if (productoEntity != null) {
				productoRepository.deleteById(id);
				productoCategoriaDeleted = productoMapper.entity2DTO(productoEntity);
				return ResponseEntity.status(HttpStatus.OK).body(productoCategoriaDeleted);
			} else {
				String error = "productoRepository.findById id=" + id + " no encontrado";
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


}
