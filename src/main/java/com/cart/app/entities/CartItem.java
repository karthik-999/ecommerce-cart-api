package  com.cart.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "TBL_CART_ITEM")
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name ="OFFER_ID", nullable = false)
	private Long offerId;
	
	@Column(name ="PRODUCT_ID", nullable = false)
	private Long productId;
	
	@Column(name ="QUANTITY", nullable = false)
	private int quantity;
	
	@Column(name ="UNIT", nullable = false)
	private String unit;
	
	@Column(name ="PRICE", nullable = false)
	private Double price;
	
	@Column(name ="FFM_TYPE", nullable = false)
	private String ffmType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CART_ID", nullable = false)
	@JsonIgnore
	private Cart cart;

}
