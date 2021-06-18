package com.cart.app.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.cart.app.entities.CartItem;
import com.cart.app.requests.CreateCartItemDTO;

public interface CartItemService {

   CartItem save(CartItem cartItem);
   Optional<CartItem> get(long id);
   Page<CartItem> getCartItemsByPage(Integer pageNumber, Integer pageSize);
   Iterable<CartItem> getCartItems();
   void update(long id, CartItem cartItem);
   void delete(long id);
   CartItem save(CreateCartItemDTO cartItem);
}
