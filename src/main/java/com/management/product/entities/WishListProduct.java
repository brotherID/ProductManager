package com.management.product.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
public class WishListProduct extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idWishListProduct;
    private String idWishlist;
    private Long idProduct;
}
