package com.management.product.api;

import com.management.product.dtos.WishListProductDto;
import com.management.product.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/wishList")
@RequiredArgsConstructor
public class WishListControllerImpl implements WishListController{
    private final WishlistService wishlistService;
    @Override
    public ResponseEntity<WishListProductDto> addProductToWishList(Long idProduct) {
        WishListProductDto wishListProductDto =  wishlistService.addProductToWishlist(idProduct);
        return ResponseEntity
                .ok(wishListProductDto);
    }
}
