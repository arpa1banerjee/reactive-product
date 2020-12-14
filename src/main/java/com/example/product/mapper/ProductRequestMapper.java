package com.example.product.mapper;

import com.example.product.dto.ProductRequest;
import com.example.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductRequestMapper {
    ProductRequestMapper INSTANCE = Mappers.getMapper( ProductRequestMapper.class );

    @Mapping(target = "id",
            expression = "java(java.util.UUID.randomUUID().toString())")
    Product productDtoToProduct(ProductRequest productRequest);

}
