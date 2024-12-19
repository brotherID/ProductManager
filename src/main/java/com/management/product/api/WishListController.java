package com.management.product.api;

import com.management.product.dtos.WishListProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface WishListController {
    String URI_ID_PRODUCT = "/{idProduct}";
    String PATH_VARIABLE_ID_PRODUCT = "idProduct";
    @PostMapping(value =URI_ID_PRODUCT)
    ResponseEntity<WishListProductDto> addProductToWishList(@PathVariable(name = PATH_VARIABLE_ID_PRODUCT) Long idProduct);
}
