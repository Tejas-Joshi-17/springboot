package com.sarvatra.services;

import com.sarvatra.dto.LoginDto;
import com.sarvatra.dto.SignUpDto;
import com.sarvatra.dto.UserDto;
import com.sarvatra.dto.response.LoginResponseDto;
import com.sarvatra.entities.User;
import com.sarvatra.repositories.SessionRepository;
import com.sarvatra.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final SessionService sessionService;

    public UserDto signUp(SignUpDto signUpDto) {
        final Optional<User> userData = userRepository.findByEmail(signUpDto.getEmail());
        if (userData.isPresent()) {
            throw new BadCredentialsException("User with email " + signUpDto.getEmail() + " Already present");
        }

        User user = User.builder()
                .name(signUpDto.getName())
                .email(signUpDto.getEmail())
                .password(signUpDto.getPassword())
                .build();

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        final User savedUser = userRepository.save(user);
        return UserDto.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .build();
    }

    public LoginResponseDto login(LoginDto loginDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        log.info("principal is :- {}", authentication.getPrincipal());

        User user = (User) authentication.getPrincipal();
        final String accessToken =  jwtTokenService.generateAccessToken(user);
        final String refreshToken = jwtTokenService.generateRefreshToken(user);

        sessionService.generateNewSession(user, refreshToken);

        return new LoginResponseDto(Boolean.TRUE, accessToken, refreshToken);
    }

    public LoginResponseDto refreshToken(String refreshToken) {
        Long userId = jwtTokenService.getUserIdFromToken(refreshToken);
        sessionService.validateSession(refreshToken);

        User user = userService.getUserById(userId);

        final String accessToken =  jwtTokenService.generateAccessToken(user);
        return new LoginResponseDto(Boolean.TRUE, accessToken, refreshToken);
    }

}
