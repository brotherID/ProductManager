package com.management.product.api;

import com.management.product.config.security.AuthenticationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationController {


    String URI_TOKEN = "/token";

    @PostMapping(value = URI_TOKEN)
    public ResponseEntity<?> getToken(@RequestBody AuthenticationRequest authenticationRequest);
}
