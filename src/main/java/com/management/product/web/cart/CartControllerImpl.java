package com.management.product.web.cart;

import com.management.product.dtos.cart.ShoppedProductResponse;
import com.management.product.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class CartControllerImpl implements CartController {
    private final CartService cartService;
    @Override
    public ResponseEntity<ShoppedProductResponse> addProductToCart(Long idProduct,Integer quantityRequested) {
        ShoppedProductResponse ShoppedProductResponse = cartService.addProductToCart(idProduct,quantityRequested);
        return ResponseEntity
                .ok(ShoppedProductResponse);
    }
}
