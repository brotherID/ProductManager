package com.management.product.service;

import com.management.product.dtos.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    List<ProductDto> getAllProduct();
    Optional<ProductDto> getProductById(Long idProduct);
    ProductDto updateProductById(Long idProduct,ProductDto productDto);
    void  removeProductById(Long idProduct);
    boolean isAdmin();
}
