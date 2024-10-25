package org.example.movieweb.repository;

import java.util.Optional;
import org.example.movieweb.entity.TokenConfirm;
import org.example.movieweb.model.enums.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokeConfirmRepository extends JpaRepository<TokenConfirm, Integer> {

  Optional<TokenConfirm> findByTokenAndType(String token, TokenType tokenType);
}
