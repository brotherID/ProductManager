package com.management.product.api;

import com.management.product.dtos.UserInfoDto;
import com.management.product.dtos.UserInfoDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserController {
    String URI_USER = "/user";
    @PostMapping(value = URI_USER)
    ResponseEntity<UserInfoDtoResponse> addUser(@RequestBody UserInfoDto userInfoDto) ;
}
