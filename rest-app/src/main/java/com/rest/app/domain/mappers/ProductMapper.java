package com.rest.app.domain.mappers;

import com.rest.app.domain.DTOS.ProductDTO;
import com.rest.app.domain.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDTO(Product product);

    Product toEntity(ProductDTO productDTO);
}
