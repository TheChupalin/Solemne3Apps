package com.microservice.microservicio1.interfaces;

import com.microservice.microservicio1.dto.CategoriaProductoDTO;
import com.microservice.microservicio1.entity.CategoriaProductoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-30T09:16:56-0400",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.46.100.v20260624-0231, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class ICategoriaProductoMapperImpl implements ICategoriaProductoMapper {

    @Override
    public CategoriaProductoDTO entity2DTO(CategoriaProductoEntity categoriaProductoEntity) {
        if ( categoriaProductoEntity == null ) {
            return null;
        }

        CategoriaProductoDTO.CategoriaProductoDTOBuilder categoriaProductoDTO = CategoriaProductoDTO.builder();

        categoriaProductoDTO.descripcion( categoriaProductoEntity.getDescripcion() );
        categoriaProductoDTO.id( categoriaProductoEntity.getId() );
        categoriaProductoDTO.nombre( categoriaProductoEntity.getNombre() );

        return categoriaProductoDTO.build();
    }

    @Override
    public CategoriaProductoEntity dTO2Entity(CategoriaProductoDTO categoriaProductoDTO) {
        if ( categoriaProductoDTO == null ) {
            return null;
        }

        CategoriaProductoEntity categoriaProductoEntity = new CategoriaProductoEntity();

        categoriaProductoEntity.setDescripcion( categoriaProductoDTO.getDescripcion() );
        categoriaProductoEntity.setId( categoriaProductoDTO.getId() );
        categoriaProductoEntity.setNombre( categoriaProductoDTO.getNombre() );

        return categoriaProductoEntity;
    }

    @Override
    public List<CategoriaProductoDTO> toListDTO(Iterable<CategoriaProductoEntity> iterable) {
        if ( iterable == null ) {
            return null;
        }

        List<CategoriaProductoDTO> list = new ArrayList<CategoriaProductoDTO>();
        for ( CategoriaProductoEntity categoriaProductoEntity : iterable ) {
            list.add( entity2DTO( categoriaProductoEntity ) );
        }

        return list;
    }
}
