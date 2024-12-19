package com.management.product.dtos;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class WishlistDto {
    private String idWishlist;
    private String email;
}
