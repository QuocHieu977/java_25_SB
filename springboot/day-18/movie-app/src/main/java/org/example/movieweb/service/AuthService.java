package org.example.movieweb.service;

import jakarta.servlet.http.HttpSession;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.movieweb.entity.TokenConfirm;
import org.example.movieweb.entity.User;
import org.example.movieweb.exception.BadRequestException;
import org.example.movieweb.exception.NotFoundException;
import org.example.movieweb.model.enums.TokenType;
import org.example.movieweb.model.enums.UserRole;
import org.example.movieweb.model.request.ForgotPasswordRequest;
import org.example.movieweb.model.request.LoginRequest;
import org.example.movieweb.model.request.RegisterRequest;
import org.example.movieweb.model.request.ResetPasswordRequest;
import org.example.movieweb.model.response.TokenConfirmMessageResponse;
import org.example.movieweb.repository.TokeConfirmRepository;
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
    private final TokeConfirmRepository tokeConfirmRepository;
    private final MailService mailService;

    public void login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("user not found"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid password");
        }

        if(!user.getIsActive()) {
            throw new BadRequestException("User is not active");
        }

        // có thể lưu user trong cookie và redis, database, file,...
        session.setAttribute("currentUser", user);
    }

    public void logout() {
        session.getAttribute("currentUser");
    }

    public void register(RegisterRequest request) {

        if (request.getName().isEmpty()) {
            throw new BadRequestException("name is empty");
        }

        if (request.getPassword().isEmpty()) {
            throw new BadRequestException("password is empty");
        }

        if (request.getConfirmPassword().isEmpty()) {
            throw new BadRequestException("confirm password is empty");
        }

        Optional<User> userOptional = userRepository.findByEmail(request.getMail());
        if (userOptional.isPresent()) {
            throw new BadRequestException("Email đã được đăng ký");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("password not matches");
        }

        String endCodePassword = passwordEncoder.encode(request.getConfirmPassword());

        User user = User.builder()
                .name(request.getName())
                .email(request.getMail())
                .avatar("https://placehold.co/200x200?text=" + request.getName().substring(0, 1).toUpperCase())
                .isActive(false)
                .password(endCodePassword)
                .role(UserRole.USER)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        userRepository.save(user);

        // sinh token
        TokenConfirm tokenConfirm = TokenConfirm.builder()
            .token(UUID.randomUUID().toString())
            .type(TokenType.CONFIRM_REGISTRATION)
            .user(user)
            .createdAt(LocalDateTime.now())
            .expiredAt(LocalDateTime.now().plusHours(1))
            .build();
        tokeConfirmRepository.save(tokenConfirm);

        // gui email xac nhan
        String link = "http://localhost:8080/xac-thuc-tai-khoan?token=" + tokenConfirm.getToken();
        System.out.println("Link xac thuc: " + link);

        mailService.sendMailRegistration(user.getEmail(),
            "xac thuc tai khoan",
            "Link xac thuc tai khoan: " + link);
    }

    public TokenConfirmMessageResponse verifyAccount(String token) {
        // xem toke co ton tai hay khong
        Optional<TokenConfirm> tokenConfirmOptional = tokeConfirmRepository.findByTokenAndType(token, TokenType.CONFIRM_REGISTRATION);

        if(tokenConfirmOptional.isEmpty()){
            return TokenConfirmMessageResponse.builder()
                .isSuccess(false)
                .message("Token khong ton tai")
                .build();
        }

        // ktra xem token da xac thuc hay chua
        TokenConfirm tokenConfirm = tokenConfirmOptional.get();
        if (tokenConfirm.getConfirmedAt() != null) {
            return TokenConfirmMessageResponse.builder()
                .isSuccess(false)
                .message("Token da xac thuc")
                .build();
        }


        if (tokenConfirm.getExpiredAt().isBefore(LocalDateTime.now())) {
            return TokenConfirmMessageResponse.builder()
                .isSuccess(false)
                .message("Token da het han")
                .build();
        }

        // xac thuc tai khoan
        User user = tokenConfirm.getUser();
        user.setIsActive(true);
        userRepository.save(user);

        tokenConfirm.setConfirmedAt(LocalDateTime.now());
        tokeConfirmRepository.save(tokenConfirm);

        return TokenConfirmMessageResponse.builder()
            .isSuccess(true)
            .message("Xac thuc tai khoan thanh cong")
            .build();
    }

    public void forgotPassword(ForgotPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new NotFoundException("user not found"));

        if(request.getEmail().isEmpty()) {
            throw new BadRequestException("email is empty");
        }

        // sinh token
        TokenConfirm tokenConfirm = TokenConfirm.builder()
            .token(UUID.randomUUID().toString())
            .type(TokenType.RESET_PASSWORD)
            .user(user)
            .createdAt(LocalDateTime.now())
            .expiredAt(LocalDateTime.now().plusHours(1))
            .build();
        tokeConfirmRepository.save(tokenConfirm);

        // gui email xac nhan
        String link = "http://localhost:8080/dat-lai-mat-khau?token=" + tokenConfirm.getToken();
        System.out.println("Link xac thuc: " + link);

        mailService.sendMailRegistration(user.getEmail(),
            "quen mat khau",
            "Link quan mat khau: " + link);
    }

    public TokenConfirmMessageResponse verifyPassword(String token) {
        // xem toke co ton tai hay khong
        Optional<TokenConfirm> tokenConfirmOptional = tokeConfirmRepository.findByTokenAndType(token, TokenType.RESET_PASSWORD);

        if(tokenConfirmOptional.isEmpty()){
            return TokenConfirmMessageResponse.builder()
                .isSuccess(false)
                .message("Token khong ton tai")
                .build();
        }

        // ktra xem token da xac thuc hay chua
        TokenConfirm tokenConfirm = tokenConfirmOptional.get();
        if (tokenConfirm.getExpiredAt().isBefore(LocalDateTime.now())) {
            return TokenConfirmMessageResponse.builder()
                .isSuccess(false)
                .message("Token da het han")
                .build();
        }

        if (tokenConfirm.getExpiredAt().isBefore(LocalDateTime.now())) {
            return TokenConfirmMessageResponse.builder()
                .isSuccess(false)
                .message("Token da het han")
                .build();
        }

        // xac thuc tai khoan
        User user = tokenConfirm.getUser();
        user.setIsActive(true);
        userRepository.save(user);

        tokenConfirm.setConfirmedAt(LocalDateTime.now());
        tokeConfirmRepository.save(tokenConfirm);

        return TokenConfirmMessageResponse.builder()
            .isSuccess(true)
            .message("doi mat khau")
            .build();
    }

    public void resetPassword(ResetPasswordRequest request) {
        Optional<TokenConfirm> tokenConfirmOptional = tokeConfirmRepository.findByTokenAndType(
            request.getToken(), TokenType.RESET_PASSWORD);

        if(tokenConfirmOptional.isEmpty()){
            throw new NotFoundException("Token not found");
        }

        if (request.getPassword().isEmpty()) {
            throw new BadRequestException("password not empty");
        }

        if (request.getConfirmPassword().isEmpty()) {
            throw new BadRequestException("password confirm not empty");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("password not matches password confirm");
        }

        String encodePassword = passwordEncoder.encode(request.getConfirmPassword());

        TokenConfirm tokenConfirm = tokenConfirmOptional.get();
        User user = tokenConfirm.getUser();
        user.setPassword(encodePassword);

        tokenConfirm.setConfirmedAt(LocalDateTime.now());
        tokeConfirmRepository.save(tokenConfirm);

        userRepository.save(user);
    }
}
