package com.cart.app.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "TBL_CART")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name ="CART_TOTAL",columnDefinition="double precision default '0'")
	private Double cartTotal = 0.0;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CART_DATE", unique = false, nullable = false, length = 10)
	private Date cartDate;
	
	@OneToOne
	@JoinColumn(name="USER_PROFILE_ID")
	@JsonIgnore
	private UserEntity user;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cart")
	@JsonIgnore
	private Set<CartItem> cartItems = new HashSet<CartItem>();

	@Column(name = "isActive")
	private boolean isActive = false;
}
