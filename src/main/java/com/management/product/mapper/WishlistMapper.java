package com.management.product.mapper;

import com.management.product.dtos.WishlistDto;
import com.management.product.entities.Wishlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WishlistMapper {
        @Mapping(source = "idWishlist", target = "idWishlist")
        @Mapping(source = "email", target = "email")
        WishlistDto toWishlistDto(Wishlist wishlist);
        @Mapping(source = "idWishlist", target = "idWishlist")
        @Mapping(source = "email", target = "email")
        Wishlist toWishlist(WishlistDto wishlistDto);
}
