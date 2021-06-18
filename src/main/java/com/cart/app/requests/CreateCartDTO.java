package com.cart.app.requests;

import java.util.Date;

import lombok.Data;

@Data
public class CreateCartDTO {

	private Double cartTotal = 0.0;
	private Date cartDate;
	private Long user;
//	private Set<CartItem> cartItems = new HashSet<CartItem>();

}
