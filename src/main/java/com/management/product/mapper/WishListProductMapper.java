package com.management.product.mapper;

import com.management.product.dtos.WishListProductDto;
import com.management.product.entities.WishListProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WishListProductMapper {
    @Mapping(source = "idWishListProduct", target = "idWishListProduct")
    @Mapping(source = "idWishlist", target = "idWishlist")
    @Mapping(source = "idProduct", target = "idProduct")
    WishListProductDto toWishListProductDto(WishListProduct wishListProduct);
    @Mapping(source = "idWishListProduct", target = "idWishListProduct")
    @Mapping(source = "idWishlist", target = "idWishlist")
    @Mapping(source = "idProduct", target = "idProduct")
    WishListProduct toWishListProduct(WishListProductDto wishListProductDto);
}
