package com.cart.app.requests;

import lombok.Data;

@Data
public class CreateCartItemDTO {

	private Long offerId;

	private Long productId;

	private int quantity;

	private String unit;

	private Double price;

	private String ffmType;

	private Long cart;
}
