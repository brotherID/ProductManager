package com.management.product.api;

import com.management.product.dtos.ShoppedProductDto;
import com.management.product.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class CartControllerImpl implements CartController{
    private final CartService cartService;
    @Override
    public ResponseEntity<ShoppedProductDto> addProductToCart(Long idProduct,Integer quantityRequested) {
        ShoppedProductDto shoppedProductDto = cartService.addProductToCart(idProduct,quantityRequested);
        return ResponseEntity
                .ok(shoppedProductDto);
    }
}
