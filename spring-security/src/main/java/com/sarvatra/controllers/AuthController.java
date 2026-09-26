package com.sarvatra.controllers;

import com.sarvatra.dto.LoginDto;
import com.sarvatra.dto.SignUpDto;
import com.sarvatra.dto.UserDto;
import com.sarvatra.dto.response.LoginResponseDto;
import com.sarvatra.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private final AuthService authService;

    @Value("${service.deployment.environment:test}")
    private String deployEnv;

    @PostMapping(path = "/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {
        final UserDto userDto = authService.signUp(signUpDto);
        return new ResponseEntity<>(userDto, HttpStatusCode.valueOf(201));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto,
                                                  HttpServletResponse httpServletResponse) {
        final LoginResponseDto loginResponseDto = authService.login(loginDto);

        Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(Boolean.TRUE);
        cookie.setSecure("production".equalsIgnoreCase(deployEnv));
        httpServletResponse.addCookie(cookie);

        return new ResponseEntity<>(loginResponseDto, HttpStatusCode.valueOf(200));
    }

    @PostMapping(path = "/refresh")
    public ResponseEntity<LoginResponseDto> login(HttpServletRequest httpServletRequest) {
        final String refreshToken = Arrays.stream(httpServletRequest.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthenticationServiceException("Refresh Token not found inside the Cookies"));

        final LoginResponseDto loginResponseDto = authService.refreshToken(refreshToken);
        return new ResponseEntity<>(loginResponseDto, HttpStatusCode.valueOf(200));
    }



}