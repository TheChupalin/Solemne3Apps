package com.microservice.microservicio1.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservice.microservicio1.dto.CategoriaProductoDTO;
import com.microservice.microservicio1.entity.CategoriaProductoEntity;
import com.microservice.microservicio1.interfaces.ICategoriaProductoMapper;
import com.microservice.microservicio1.interfaces.ICategoriaProductoService;
import com.microservice.microservicio1.repository.ICategoriaProductoRepository;

@Service
public class CategoriaProductoServiceImpl implements ICategoriaProductoService {

	private Logger logger = LoggerFactory.getLogger(CategoriaProductoServiceImpl.class);

	@Autowired
	private ICategoriaProductoRepository categoriaProductoRepository;

	@Autowired
	private ICategoriaProductoMapper categoriaProductoMapper;

	@Override
	public ResponseEntity<List<CategoriaProductoDTO>> findAll() throws Exception {
		logger.info("Micro1 CategoriaProductoServiceImpl findAll");
		List<CategoriaProductoEntity> categoriaProductoEntityList;
		List<CategoriaProductoDTO> categoriaProductoDTOList = null;
		try {
			categoriaProductoEntityList = categoriaProductoRepository.findAll();
			categoriaProductoDTOList = categoriaProductoMapper.toListDTO(categoriaProductoEntityList);
			return ResponseEntity.status(HttpStatus.OK).body(categoriaProductoDTOList);
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<CategoriaProductoDTO> findbyId(long id) throws Exception {
		logger.info("Micro1 CategoriaProductoServiceImpl findbyId");
		Optional<CategoriaProductoEntity> optionalCategoriaProductoEntity;
		CategoriaProductoEntity categoriaProductoEntity;
		CategoriaProductoDTO categoriaProductoDTO = null;
		try {
			optionalCategoriaProductoEntity = categoriaProductoRepository.findById(id);
			categoriaProductoEntity = optionalCategoriaProductoEntity.orElse(null);
			if (categoriaProductoEntity != null) {
				categoriaProductoDTO = categoriaProductoMapper.entity2DTO(categoriaProductoEntity);
				return ResponseEntity.status(HttpStatus.OK).body(categoriaProductoDTO);
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
	public ResponseEntity<CategoriaProductoDTO> create(CategoriaProductoDTO categoriaProductoDTO) throws Exception {
		logger.info("Micro1 CategoriaProductoServiceImpl create");
		CategoriaProductoEntity categoriaProductoEntity2Save;
		CategoriaProductoEntity categoriaProductoEntitySaved;
		CategoriaProductoDTO categoriaProductoDTOSaved = null;
		try {
			categoriaProductoEntity2Save = categoriaProductoMapper.dTO2Entity(categoriaProductoDTO);
			categoriaProductoEntitySaved = categoriaProductoRepository.save(categoriaProductoEntity2Save);
			categoriaProductoDTOSaved = categoriaProductoMapper.entity2DTO(categoriaProductoEntitySaved);
			return ResponseEntity.status(HttpStatus.CREATED).body(categoriaProductoDTOSaved);
		} catch (Exception e) {
			logger.error(new Object() {
			}.getClass().getEnclosingMethod().getName() + " " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<CategoriaProductoDTO> update(long id, CategoriaProductoDTO categoriaProductoDTO)
			throws Exception {
		logger.info("Micro1 CategoriaProductoServiceImpl update");
		Optional<CategoriaProductoEntity> optionalCategoriaProductoEntity;
		CategoriaProductoEntity categoriaProductoEntity;
		CategoriaProductoEntity categoriaProductoEntity2Save;
		CategoriaProductoEntity categoriaProductoEntitySaved;
		CategoriaProductoDTO categoriaProductoDTOSaved;
		try {
			optionalCategoriaProductoEntity = categoriaProductoRepository.findById(id);
			categoriaProductoEntity = optionalCategoriaProductoEntity.orElse(null);
			if (categoriaProductoEntity != null) {
				categoriaProductoEntity2Save = categoriaProductoMapper.dTO2Entity(categoriaProductoDTO);
				categoriaProductoEntity2Save.setId(id);
				categoriaProductoEntitySaved = categoriaProductoRepository.save(categoriaProductoEntity2Save);
				categoriaProductoDTOSaved = categoriaProductoMapper.entity2DTO(categoriaProductoEntitySaved);
				return ResponseEntity.status(HttpStatus.OK).body(categoriaProductoDTOSaved);
			} else {
				String error = "categoriaProductoRepository.findById id=" + id + " no encontrado";
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
	public ResponseEntity<CategoriaProductoDTO> deleteById(long id) throws Exception {
		logger.info("Micro1 CategoriaProductoServiceImpl deleteById");
		Optional<CategoriaProductoEntity> optionalCategoriaProductoEntity;
		CategoriaProductoEntity categoriaProductoEntity;
		CategoriaProductoDTO categoriaProductoCategoriaDeleted;
		try {
			optionalCategoriaProductoEntity = categoriaProductoRepository.findById(id);
			categoriaProductoEntity = optionalCategoriaProductoEntity.orElse(null);
			if (categoriaProductoEntity != null) {
				categoriaProductoRepository.deleteById(id);
				categoriaProductoCategoriaDeleted = categoriaProductoMapper.entity2DTO(categoriaProductoEntity);
				return ResponseEntity.status(HttpStatus.OK).body(categoriaProductoCategoriaDeleted);
			} else {
				String error = "categoriaProductoRepository.findById id=" + id + " no encontrado";
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
