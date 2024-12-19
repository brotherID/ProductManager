package com.management.product.api;

import com.management.product.dtos.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface ProductController {
    String PATH_VARIABLE_ID_PRODUCT = "idProduct";
    String URI_ID_PRODUCT = "/{idProduct}";
    String URI_CART_USER = "/cart-user";
    String URI_WISH_LIST = "/wish-list";
    @PostMapping()
    ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto);
    @GetMapping()
    ResponseEntity<List<ProductDto>> getProducts();
    @GetMapping(value = URI_ID_PRODUCT)
    ResponseEntity<ProductDto> getProductById(@PathVariable(name = PATH_VARIABLE_ID_PRODUCT) Long idProduct);
    @PatchMapping(value = URI_ID_PRODUCT)
    ResponseEntity<ProductDto> updateProductById(@PathVariable(name = PATH_VARIABLE_ID_PRODUCT) Long idProduct , @RequestBody ProductDto productDto);
    @DeleteMapping(value = URI_ID_PRODUCT)
    ResponseEntity<Void> deleteProductById(@PathVariable(name = PATH_VARIABLE_ID_PRODUCT) Long idProduct );
    @GetMapping(value = URI_CART_USER)
    ResponseEntity<List<ProductDto>> getProductsOfCartUser();
    @GetMapping(value = URI_WISH_LIST)
    ResponseEntity<List<ProductDto>> getProductsOfWishlistUser();
}
