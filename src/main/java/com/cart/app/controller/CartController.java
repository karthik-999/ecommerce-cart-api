package com.cart.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cart.app.entities.Cart;
import com.cart.app.requests.CreateCartDTO;
import com.cart.app.service.interfaces.ICartService;

@RestController
@RequestMapping("/cart/")
public class CartController {

	@Autowired
	ICartService CartService;

	@GetMapping("/all/{page}/{size}")
	public ResponseEntity<List<Cart>> getCartsByPage(@PathVariable(value = "page", required = true) int page,
			@PathVariable(value = "size", required = true) int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
		List<Cart> Carts = CartService.findAll(pageable);
		return new ResponseEntity<>(Carts, HttpStatus.OK);
	}

	@GetMapping("/get/{CartId}")
	public ResponseEntity<Cart> getCart(@PathVariable("CartId") Long CartId) {
		return new ResponseEntity<>(CartService.getCart(CartId), HttpStatus.OK);
	}

	@GetMapping("/getcart/{userId}")
	public ResponseEntity<Cart> getCartByUserId(@PathVariable("userId") Long userId) {
		return new ResponseEntity<>(CartService.getCartByUserId(userId), HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Cart> saveCart(@RequestBody CreateCartDTO user) {
		return new ResponseEntity<>(CartService.saveCart(user), HttpStatus.CREATED);
	}
}
