package com.management.product.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
public class Wishlist extends Auditable  {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String   idWishlist;
    @Column(unique = true)
    private String email;
}
