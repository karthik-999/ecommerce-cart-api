package com.cart.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cart.app.entities.CartItem;

public interface CartItemDaoRepository extends PagingAndSortingRepository<CartItem, Long> {

	Page<CartItem> findAll(Pageable pageable);
}
