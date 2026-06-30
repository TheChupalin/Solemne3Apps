package com.microservice.microservicio2.interfaces;

import com.microservice.microservicio2.dto.ProductoDTO;
import com.microservice.microservicio2.entity.ProductoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-30T09:16:57-0400",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.46.100.v20260624-0231, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class IProductoMapperImpl implements IProductoMapper {

    @Override
    public ProductoDTO entity2DTO(ProductoEntity ProductoEntity) {
        if ( ProductoEntity == null ) {
            return null;
        }

        ProductoDTO.ProductoDTOBuilder productoDTO = ProductoDTO.builder();

        productoDTO.descripcion( ProductoEntity.getDescripcion() );
        productoDTO.id( ProductoEntity.getId() );
        productoDTO.idCategoria( ProductoEntity.getIdCategoria() );
        productoDTO.nombre( ProductoEntity.getNombre() );
        productoDTO.precio( ProductoEntity.getPrecio() );
        productoDTO.stock( ProductoEntity.getStock() );

        return productoDTO.build();
    }

    @Override
    public ProductoEntity dTO2Entity(ProductoDTO ProductoDTO) {
        if ( ProductoDTO == null ) {
            return null;
        }

        ProductoEntity productoEntity = new ProductoEntity();

        productoEntity.setDescripcion( ProductoDTO.getDescripcion() );
        productoEntity.setId( ProductoDTO.getId() );
        productoEntity.setIdCategoria( ProductoDTO.getIdCategoria() );
        productoEntity.setNombre( ProductoDTO.getNombre() );
        productoEntity.setPrecio( ProductoDTO.getPrecio() );
        productoEntity.setStock( ProductoDTO.getStock() );

        return productoEntity;
    }

    @Override
    public List<ProductoDTO> toListDTO(Iterable<ProductoEntity> iterable) {
        if ( iterable == null ) {
            return null;
        }

        List<ProductoDTO> list = new ArrayList<ProductoDTO>();
        for ( ProductoEntity productoEntity : iterable ) {
            list.add( entity2DTO( productoEntity ) );
        }

        return list;
    }
}
