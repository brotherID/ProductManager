package com.management.product.config.security;

import com.management.product.entities.UserInfo;
import com.management.product.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;


@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserInfoRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserInfo user = userRepository.findByEmail(email) .orElseThrow(() ->
                new UsernameNotFoundException("User not exists by Username or Email"));
        return new User(
                email,
                user.getPassword(),
                new ArrayList<>()
        );
    }
}
