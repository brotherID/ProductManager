package com.management.product.repository;


import com.management.product.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Integer> {
       Optional<Cart> findByEmail(String email);
}
