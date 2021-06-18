package com.cart.app.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.cart.app.entities.Cart;
import com.cart.app.requests.CreateCartDTO;

public interface ICartService {

	public List<Cart> findAll(Pageable pageable);

	public Cart getCart(Long CartId);

	public Cart saveCart(Cart Cart);

	public Cart getCartByUserId(Long userId);

	Cart saveCart(CreateCartDTO cartDTO);

}
