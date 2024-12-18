package org.example.movieweb.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TokenConfirmMessageResponse {
  Boolean isSuccess;
  String message;
}
