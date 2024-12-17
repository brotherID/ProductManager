package com.management.product.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UserInfoDto {
    private String username;
    private String firstname;
    private String email;
    private String password;
}
