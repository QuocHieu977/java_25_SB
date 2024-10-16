package org.example.movieweb.rest;

import lombok.RequiredArgsConstructor;
import org.example.movieweb.entity.User;
import org.example.movieweb.model.request.UpdateNameRequest;
import org.example.movieweb.model.request.UpdatePasswordRequest;
import org.example.movieweb.model.response.ErrorResponse;
import org.example.movieweb.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;

    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateNameRequest request) {
        try {
            User user = userService.updateProfile(request);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequest request) {
        try {
            User user = userService.updatePassword(request);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
