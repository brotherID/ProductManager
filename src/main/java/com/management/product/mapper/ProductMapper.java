package com.management.product.mapper;

import com.management.product.dtos.ProductDto;
import com.management.product.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
     @Mapping(source = "codeProduct", target = "codeProduct")
     @Mapping(source = "nameProduct", target = "nameProduct")
     @Mapping(source = "descriptionProduct", target = "descriptionProduct")
     @Mapping(source = "imageProduct", target = "imageProduct")
     @Mapping(source = "categoryProduct", target = "categoryProduct")
     @Mapping(source = "priceProduct", target = "priceProduct")
     @Mapping(source = "quantityProduct", target = "quantityProduct")
     @Mapping(source = "internalReferenceProduct", target = "internalReferenceProduct")
     @Mapping(source = "shellIdProduct", target = "shellIdProduct")
     @Mapping(source = "inventoryStatus", target = "inventoryStatus")
     @Mapping(source = "ratingProduct", target = "ratingProduct")
     ProductDto fromEntity(Product product);
     @Mapping(source = "codeProduct", target = "codeProduct")
     @Mapping(source = "nameProduct", target = "nameProduct")
     @Mapping(source = "descriptionProduct", target = "descriptionProduct")
     @Mapping(source = "imageProduct", target = "imageProduct")
     @Mapping(source = "categoryProduct", target = "categoryProduct")
     @Mapping(source = "priceProduct", target = "priceProduct")
     @Mapping(source = "quantityProduct", target = "quantityProduct")
     @Mapping(source = "internalReferenceProduct", target = "internalReferenceProduct")
     @Mapping(source = "shellIdProduct", target = "shellIdProduct")
     @Mapping(source = "inventoryStatus", target = "inventoryStatus")
     @Mapping(source = "ratingProduct", target = "ratingProduct")
     Product toEntity(ProductDto productDto);
     List<Product> toProductList(List<ProductDto> productDtos);
     List<ProductDto> toProductDtoList(List<Product> products);
}
