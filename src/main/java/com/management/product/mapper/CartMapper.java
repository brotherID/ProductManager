package com.management.product.mapper;

import com.management.product.dtos.CartDto;
import com.management.product.entities.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(source = "expirationDate", target = "expirationDate")
    @Mapping(source = "email", target = "email")
    CartDto toCartDto(Cart cart);
    @Mapping(source = "expirationDate", target = "expirationDate")
    @Mapping(source = "email", target = "email")
    Cart toCart(CartDto cartDto);
}
