package com.management.product.mapper;

import com.management.product.dtos.UserInfoDto;
import com.management.product.dtos.UserInfoDtoResponse;
import com.management.product.entities.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserInfoMapper {
    @Mapping(source = "username", target = "username")
    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    UserInfoDto toUserInfoDto(UserInfo userInfo);
    @Mapping(source = "username", target = "username")
    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    UserInfo toUserInfo(UserInfoDto userInfoDto);
    @Mapping(source = "username", target = "username")
    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "email", target = "email")
    UserInfoDtoResponse toUserInfoDtoResponse(UserInfo userInfo);
}
