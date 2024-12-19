package com.management.product.api;


import com.management.product.dtos.ShoppedProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface CartController {
    String URI_ID_PRODUCT = "/{idProduct}";
    String URI_QUANTITY_REQUESTED = "/{quantityRequested}";
    String PATH_VARIABLE_ID_PRODUCT = "idProduct";
    String PATH_VARIABLE_QUANTITY_REQUESTED = "quantityRequested";
    @PostMapping(value =URI_ID_PRODUCT+URI_QUANTITY_REQUESTED+"/cart" )
    ResponseEntity<ShoppedProductDto> addProductToCart(@PathVariable(name = PATH_VARIABLE_ID_PRODUCT) Long idProduct,@PathVariable(name= PATH_VARIABLE_QUANTITY_REQUESTED) Integer quantityRequested);
}
