package org.example.movieweb.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.movieweb.entity.User;
import org.example.movieweb.model.request.UpdateNameRequest;
import org.example.movieweb.model.request.UpdatePasswordRequest;
import org.example.movieweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final HttpSession session;
    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User updateProfile(UpdateNameRequest request) {
        User user = (User) session.getAttribute("currentUser");

        if (request.getName().isEmpty()) {
            throw new RuntimeException("Can not name is empty");
        }

        user.setName(request.getName());
        return userRepository.save(user);
    }

    public User updatePassword(UpdatePasswordRequest request) {
        User user = (User) session.getAttribute("currentUser");

        if (request.getOldPassword().isEmpty()) {
            throw new RuntimeException("Old password is empty");
        }
        if (request.getNewPassword().isEmpty()) {
            throw new RuntimeException("Old password is empty");
        }
        if (request.getConfirmPassword().isEmpty()) {
            throw new RuntimeException("Old password is empty");
        }
        if (request.getOldPassword().equals(request.getNewPassword())) {
            throw new RuntimeException("Old password matches new password");
        }

        if (!request.getConfirmPassword().equals(request.getNewPassword())) {
            throw new RuntimeException("Confirm password does not match new password");
        }

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }

        String encodePassword = passwordEncoder.encode(request.getConfirmPassword());

        user.setPassword(encodePassword);
        return userRepository.save(user);
    }
}
