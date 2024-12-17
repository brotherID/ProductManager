package com.management.product.api;


import com.management.product.dtos.ShoppedProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface CartController {
    String URI_ID_PRODUCT = "/{idProduct}";
    String PATH_VARIABLE_ID_PRODUCT = "idProduct";
    @PostMapping(value =URI_ID_PRODUCT+"/cart" )
    ResponseEntity<ShoppedProductDto> addProductToCart(@PathVariable(name = PATH_VARIABLE_ID_PRODUCT) Long idProduct);


}
