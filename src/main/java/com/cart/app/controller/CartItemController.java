package com.cart.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cart.app.entities.CartItem;
import com.cart.app.requests.CreateCartItemDTO;
import com.cart.app.service.interfaces.CartItemService;

@RestController
@RequestMapping(value="/cart-item/")
public class CartItemController extends AbstractController {

  
   private CartItemService  cartService;
   
   public CartItemController(CartItemService cartService) {
	   this.cartService = cartService;
   }

   /*---Add new CartItem---*/
   @PostMapping
   public ResponseEntity<?> createCartItem(@RequestBody CartItem cartItem) {
      CartItem savedCartItem = cartService.save(cartItem);
      return ResponseEntity.ok().body("New CartItem has been saved with ID:" + savedCartItem.getId());
   }

   @PostMapping("/save")
   public ResponseEntity<?> createCartItem(@RequestBody CreateCartItemDTO cartItem) {
      CartItem savedCartItem = cartService.save(cartItem);
      return ResponseEntity.ok().body("New CartItem has been saved with ID:" + savedCartItem.getId());
   }

   
   /*---Get a CartItem by id---*/
   @GetMapping("/cart-item/{id}")
   @ResponseBody
   public CartItem getCartItem(@PathVariable("id") long id) {
	  Optional<CartItem> returnedCartItem = cartService.get(id);
	  CartItem CartItem  = returnedCartItem.get(); 
      return CartItem;
   }
   
 

   /*---get all CartItem---*/
   @GetMapping("/cart-item")
   public @ResponseBody List<CartItem> getCategoriesByPage(
		   @RequestParam(value="pagenumber", required=true, defaultValue="0") Integer pageNumber,
		   @RequestParam(value="pagesize", required=true, defaultValue="20") Integer pageSize) {
      Page<CartItem> pagedCartItems = cartService.getCartItemsByPage(pageNumber, pageSize);
      return pagedCartItems.getContent();
   }
   


   /*---Update a CartItem by id---*/
   @PutMapping("/cart-item/{id}")
   public ResponseEntity<?> updateCartItem(@PathVariable("id") long id, @RequestBody CartItem cartItem) {
	  checkResourceFound(this.cartService.get(id));
	  cartService.update(id, cartItem);
      return ResponseEntity.ok().body("CartItem has been updated successfully.");
   }

   /*---Delete a CartItem by id---*/
   @DeleteMapping("/cart-item/{id}")
   public ResponseEntity<?> deleteCartItem(@PathVariable("id") long id) {
	  checkResourceFound(this.cartService.get(id));
	  cartService.delete(id);
      return ResponseEntity.ok().body("CartItem has been deleted successfully.");
   }
}