package com.microservice.microservicio2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.microservicio2.entity.ProductoEntity;

@Repository
public interface IProductoRepository extends JpaRepository<ProductoEntity, Long> {
}