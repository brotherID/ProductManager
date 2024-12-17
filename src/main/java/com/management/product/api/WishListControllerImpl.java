package com.management.product.api;

import com.management.product.dtos.WishListProductDto;
import com.management.product.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/wishList")
@RequiredArgsConstructor
public class WishListControllerImpl implements WishListController{

    private final WishlistService wishlistService;


    @Override
    public ResponseEntity<WishListProductDto> addProductToWishList(Long idProduct) {

        WishListProductDto wishListProductDto =  wishlistService.addProductToWishlist(idProduct);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .toUri();
        return ResponseEntity
                .created(location)
                .body(wishListProductDto);
    }
}