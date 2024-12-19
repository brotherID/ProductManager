package com.management.product.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
public class Cart extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCart;
    private LocalDateTime expirationDate;
    @Column(unique = true)
    private String email;
}
