package com.management.product.service;


import com.management.product.dtos.cart.ShoppedProductResponse;
import com.management.product.dtos.product.ProductResponse;

import java.util.List;

public interface CartService {
    ShoppedProductResponse addProductToCart(Long idProduct,Integer quantityRequested);
    List<ProductResponse> getProductsOfCartUser();
}
