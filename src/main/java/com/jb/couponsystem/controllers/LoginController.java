package com.jb.couponsystem.controllers;

import com.jb.couponsystem.exception.CouponSystemException;
import com.jb.couponsystem.security.LoginManager;
import com.jb.couponsystem.security.LoginRequest;
import com.jb.couponsystem.security.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/login")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LoginController {

    private final LoginManager loginManager;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws CouponSystemException {
        return loginManager.login(loginRequest.getEmail(), loginRequest.getPassword(), loginRequest.getClientType());
    }

}
