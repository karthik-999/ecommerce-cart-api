package com.cart.app.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cart.app.entities.Cart;
import com.cart.app.entities.CartItem;
import com.cart.app.repositories.CartItemDaoRepository;
import com.cart.app.repositories.CartRepository;
import com.cart.app.requests.CreateCartItemDTO;
import com.cart.app.service.interfaces.CartItemService;
import com.cart.app.service.interfaces.ICartService;

@Service
public class CartItemServiceImpl implements CartItemService {

	final static Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);

	@Autowired
	private CartItemDaoRepository cartItemDao;
	
	private CartRepository cartRepository;

	@Autowired
	private ICartService cartService = new CartServiceImpl();

	@Override
	public CartItem save(CartItem cartItem) {
		return cartItemDao.save(cartItem);
	}

	@Override
	public Optional<CartItem> get(long id) {
		return cartItemDao.findById(id);
	}

	@Override
	public Page<CartItem> getCartItemsByPage(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("productId").descending());
		return cartItemDao.findAll(pageable);
	}

	@Override
	public Iterable<CartItem> getCartItems() {
		return cartItemDao.findAll();
	}

	@Override
	public void update(long id, CartItem cartItem) {
		cartItemDao.save(cartItem);
	}

	@Override
	public void delete(long id) {
		cartItemDao.deleteById(id);
	}

	@Override
	public CartItem save(CreateCartItemDTO cartItemDTO) {
		CartItem cartItem = new CartItem();

		if (!(cartItemDTO != null && cartItemDTO.getCart() != null)) {
			throw new RuntimeException("Check Request Details for CartItem");
		}
		Cart cart = cartService.getCart(cartItemDTO.getCart());
		cartItem.setCart(cart);
		cart.setCartTotal(cart.getCartTotal()+Double.valueOf(cartItemDTO.getQuantity()*cartItemDTO.getPrice()));
		cart.setCartDate(Date.valueOf(LocalDate.now()));

		BeanUtils.copyProperties(cartItemDTO, cartItem,"cart");
		cartItem.setCart(cart);
		return cartItemDao.save(cartItem);
	}

}
