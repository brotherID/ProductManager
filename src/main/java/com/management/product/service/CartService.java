package com.management.product.service;


import com.management.product.dtos.ProductDto;
import com.management.product.dtos.ShoppedProductDto;

import java.util.List;

public interface CartService {
    ShoppedProductDto addProductToCart(Long idProduct,Integer quantityRequested);
    List<ProductDto> getProductsOfCartUser();
}
