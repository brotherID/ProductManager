package com.management.product.service;

import com.management.product.dtos.UserInfoDto;
import com.management.product.dtos.UserInfoDtoResponse;
import com.management.product.entities.UserInfo;
import com.management.product.mapper.UserInfoMapper;
import com.management.product.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;

import static org.zalando.problem.Status.CONFLICT;

@RequiredArgsConstructor
@Service
public class UserInfoServiceImpl implements UserInfoService {

    public static final String USER_FOUNDED = "User founded";
    public static final String USER_EXIST = "The user with email %s  exist.";
    private final UserInfoRepository userInfoRepository;
    private final UserInfoMapper userInfoMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserInfoDtoResponse addUser(UserInfoDto userInfoDto) {
        userInfoRepository.findByEmail(userInfoDto.getEmail())
                .ifPresent(userInfoFound -> {
                    throw Problem.builder()
                            .withTitle(USER_FOUNDED)
                            .withStatus(CONFLICT)
                            .withDetail(String.format(USER_EXIST, userInfoFound.getEmail()))
                            .build();
                });
        userInfoDto.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
        UserInfo userInfo = userInfoRepository.save(userInfoMapper.toUserInfo(userInfoDto));
        return userInfoMapper.toUserInfoDtoResponse(userInfo);
    }
}
