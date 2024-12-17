package com.management.product.dtos;

import com.management.product.entities.UserInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CartDto {
    private Long idCart;
    private LocalDateTime expirationDate;
    private String email;
}
