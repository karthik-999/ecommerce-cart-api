package com.cart.app.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cart.app.IUserService;
import com.cart.app.entities.Cart;
import com.cart.app.entities.UserEntity;
import com.cart.app.repositories.CartRepository;
import com.cart.app.requests.CreateCartDTO;
import com.cart.app.service.interfaces.ICartService;

@Service
public class CartServiceImpl implements ICartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	IUserService userService;

	@Override
	public List<Cart> findAll(Pageable pageable) {

		return cartRepository.findAll(pageable).getContent();

	}

	@Override
	public Cart getCart(Long CartId) {
		Optional<Cart> optionalCart = cartRepository.findById(CartId);
		if (optionalCart.isPresent()) {
			return optionalCart.get();
		}
		return new Cart();

	}

	@Override
	public Cart saveCart(Cart cart) {
		if (cart.getCartDate() == null) {
			cart.setCartDate(Date.valueOf(LocalDate.now()));
		}
		return cartRepository.save(cart);
	}

	@Override
	public Cart saveCart(CreateCartDTO cartDTO) {
		UserEntity user = new UserEntity();
		Cart cart = getCartByUserId(cartDTO.getUser());
		if ((cartDTO.getUser() != null && cart != null)) {
			throw new RuntimeException("User already has Cart  exists");
		}
		user = userService.getUser1(cartDTO.getUser()).getBody();
		cart = new Cart();
		cart.setCartDate(Date.valueOf(LocalDate.now()));
		cart.setUser(user);
		cart.setCartTotal(0.0);
		return cartRepository.save(cart);
	}

	@Override
	public Cart getCartByUserId(Long userId) {
		Cart cart = cartRepository.findByUserIdAndIsActiveIsTrue(userId);
		return cart;
	}
}
