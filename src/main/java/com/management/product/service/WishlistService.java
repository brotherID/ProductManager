package com.management.product.service;

import com.management.product.dtos.ProductDto;
import com.management.product.dtos.WishListProductDto;

import java.util.List;

public interface WishlistService {
    WishListProductDto addProductToWishlist(Long idProduct);
    List<ProductDto> getProductsOfWishlistUser();
}
