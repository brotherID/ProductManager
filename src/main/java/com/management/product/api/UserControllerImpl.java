package com.management.product.api;

import com.management.product.dtos.UserInfoDto;
import com.management.product.dtos.UserInfoDtoResponse;
import com.management.product.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController{
    private final UserInfoService userInfoService;
    @Override
    public ResponseEntity<UserInfoDtoResponse> addUser(UserInfoDto userInfoDto) {
        return ResponseEntity.ok(userInfoService.addUser(userInfoDto));
    }
}
