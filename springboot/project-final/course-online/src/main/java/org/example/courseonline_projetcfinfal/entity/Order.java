package org.example.courseonline_projetcfinfal.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.courseonline_projetcfinfal.model.enums.OrderStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    OrderStatus status;
    Double total_price;
    String method_payment;
    LocalDateTime payment_date;
    LocalDateTime created_at;
    LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name= "user_id")
    User user;
}
