package com.management.product.service;

import com.management.product.dtos.user.UserInfoRequest;
import com.management.product.dtos.user.UserInfoResponse;

public interface UserInfoService {
    UserInfoResponse addUser(UserInfoRequest userInfoRequest);
}
