package org.example.movieweb.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.example.movieweb.model.enums.TokenType;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "token_confirm")
public class TokenConfirm {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "token")
  private String token; // chuỗi token

  @Enumerated(EnumType.STRING)
  TokenType type;

  @Column(name = "created_at")
  private LocalDateTime createdAt; // thời gian tạo

  @Column(name = "expired_at")
  private LocalDateTime expiredAt; // thời gian hết hạn

  @Column(name = "confirmed_at")
  private LocalDateTime confirmedAt; // thời gian xác thực

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user; // token này của user nào
}

