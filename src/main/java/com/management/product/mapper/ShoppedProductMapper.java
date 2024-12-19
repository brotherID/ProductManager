package com.management.product.mapper;

import com.management.product.dtos.ShoppedProductDto;
import com.management.product.entities.ShoppedProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShoppedProductMapper {
    @Mapping(source = "idShoppedProduct", target = "idShoppedProduct")
    @Mapping(source = "idCart", target = "idCart")
    @Mapping(source = "idProduct", target = "idProduct")
    ShoppedProduct toShoppedProduct(ShoppedProductDto shoppedProductDto);
    @Mapping(source = "idShoppedProduct", target = "idShoppedProduct")
    @Mapping(source = "idCart", target = "idCart")
    @Mapping(source = "idProduct", target = "idProduct")
    ShoppedProductDto toShoppedProductDto(ShoppedProduct shoppedProduct);
}
