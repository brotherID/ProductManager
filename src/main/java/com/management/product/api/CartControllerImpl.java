package com.management.product.api;

import com.management.product.dtos.ShoppedProductDto;
import com.management.product.entities.ShoppedProduct;
import com.management.product.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class CartControllerImpl implements CartController{

    private final CartService cartService;

    @Override
    public ResponseEntity<ShoppedProductDto> addProductToCart(Long idProduct) {

        ShoppedProductDto shoppedProductDto = cartService.addProductToCart(idProduct);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .toUri();
        return ResponseEntity
                .created(location)
                .body(shoppedProductDto);

    }
}
