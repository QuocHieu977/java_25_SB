package org.example.movieweb.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.movieweb.entity.User;
import org.example.movieweb.model.enums.UserRole;
import org.example.movieweb.model.request.LoginRequest;
import org.example.movieweb.model.request.RegisterRequest;
import org.example.movieweb.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final HttpSession  session;

    public void login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("user not found"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // có thể lưu user trong cookie và redis, database, file,...
        session.setAttribute("currentUser", user);
    }

    public void logout() {
        session.getAttribute("currentUser");
    }

    public void register(RegisterRequest request) {
        if (request.getName().isEmpty()) {
            throw new RuntimeException("name is empty");
        }

        if (request.getPassword().isEmpty()) {
            throw new RuntimeException("password is empty");
        }

        if (request.getConfirmPassword().isEmpty()) {
            throw new RuntimeException("confirm password is empty");
        }

        Optional<User> userOptional = userRepository.findByEmail(request.getMail());
        if (userOptional.isPresent()) {
            throw new RuntimeException("Email đã được đăng ký");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("password not matches");
        }

        String endCodePassword = passwordEncoder.encode(request.getConfirmPassword());

        User newUser = User.builder()
                .name(request.getName())
                .email(request.getMail())
                .avatar("https://placehold.co/200x200?text=" + request.getName().substring(0, 1).toUpperCase())
                .password(endCodePassword)
                .role(UserRole.USER)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.save(newUser);
    }
}
