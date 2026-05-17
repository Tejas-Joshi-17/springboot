package com.sarvatra.controllers;

import com.sarvatra.entities.User;
import com.sarvatra.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(path = "/add-user")
    public ResponseEntity<String> addUser(@RequestBody Map<String, String> formData) {
        String username = formData.getOrDefault("name", "joshitejas188@gmail.com");
        String password = formData.getOrDefault("password", "Tejas@987");
        User user = new User();
        user.setEmail(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        return new ResponseEntity<>("User Added", HttpStatusCode.valueOf(200));
    }

}
