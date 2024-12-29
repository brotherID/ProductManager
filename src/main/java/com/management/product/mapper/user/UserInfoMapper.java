package com.management.product.mapper.user;

import com.management.product.dtos.user.UserInfoRequest;
import com.management.product.dtos.user.UserInfoResponse;
import com.management.product.entities.user.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserInfoMapper {
    @Mapping(source = "username", target = "username")
    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "email", target = "email")
    UserInfoResponse toUserInfoDto(UserInfo userInfo);
    @Mapping(source = "username", target = "username")
    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    UserInfo toUserInfo(UserInfoRequest userInfoRequest);
    @Mapping(source = "username", target = "username")
    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "email", target = "email")
    UserInfoResponse toUserInfoResponse(UserInfo userInfo);
}