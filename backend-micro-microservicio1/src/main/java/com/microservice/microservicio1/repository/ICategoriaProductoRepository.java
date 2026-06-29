package com.microservice.microservicio1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.microservicio1.entity.CategoriaProductoEntity;

@Repository
public interface ICategoriaProductoRepository extends JpaRepository<CategoriaProductoEntity, Long> {
}