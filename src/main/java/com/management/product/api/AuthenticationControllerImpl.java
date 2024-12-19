package com.management.product.api;

import com.management.product.dtos.AuthenticationRequest;
import com.management.product.dtos.AuthenticationResponse;
import com.management.product.config.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
@Slf4j
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController{
    private  final AuthenticationManager authenticationManager;
    private  final JwtTokenProvider jwtUtil;
    public ResponseEntity<?> getToken( AuthenticationRequest authenticationRequest) {
        log.info("Begin getToken : {}", authenticationRequest.toString());
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur ou mot de passe incorrect");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateToken(authentication);
        log.info("End getToken ");
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}
