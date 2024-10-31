package org.example.demospringsecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.security.Principal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
  @GetMapping("/")
  public String home() {
    return "Home page";
  }

  @Secured("ROLE_USER")
  @GetMapping("/user")
  public String user() {
    return "User page";
  }

  @GetMapping("/admin")
  public String admin() {
    return "Admin page";
  }

  @GetMapping("/info-1")
  public ResponseEntity<?> info1() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResponseEntity.ok(authentication);
  }

  @GetMapping("/info-2")
  public ResponseEntity<?> info2(Principal principal) {
    return ResponseEntity.ok(principal);
  }

  @GetMapping("/info-3")
  public ResponseEntity<?> info3(Authentication authentication) {
    return ResponseEntity.ok(authentication);
  }

  @GetMapping("/info-4")
  public ResponseEntity<?> info3(HttpServletRequest request) {
    return ResponseEntity.ok(request.getUserPrincipal());
  }
}
