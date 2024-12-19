package com.management.product.service;

import com.management.product.dtos.UserInfoDto;
import com.management.product.dtos.UserInfoDtoResponse;

public interface UserInfoService {
    UserInfoDtoResponse addUser(UserInfoDto userInfoDto);
}
