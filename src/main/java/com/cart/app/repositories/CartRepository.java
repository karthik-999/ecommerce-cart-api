package com.cart.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cart.app.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	Cart findByUserId(Long userId);

	Cart findByUserIdAndIsActiveIsTrue(Long userId);

}
