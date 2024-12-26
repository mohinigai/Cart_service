package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.model.Cart;
import com.task.service.CartServiceI;

@RestController
@RequestMapping("/api")
public class CartController {
	@Autowired
	private CartServiceI cartservice;

	@PostMapping("/cart")
	ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
		Cart carts = cartservice.createCart(cart);
		return new ResponseEntity<>(carts, HttpStatus.CREATED);
	}

	@GetMapping("/cart")
	ResponseEntity<List<Cart>> getAllCart() {
		List<Cart> allCart = cartservice.getAllCart();
		return new ResponseEntity<List<Cart>>(allCart, HttpStatus.OK);
	}
	@PutMapping("/cart/{id}")
	ResponseEntity<Cart> updateCarts(@RequestBody Cart cart,@PathVariable ("id") Long cartId) {
		Cart updatedcart= cartservice.updateCart(cart, cartId);
		return new ResponseEntity<Cart>(updatedcart, HttpStatus.CREATED);
	}
	@GetMapping("/cart/{id}")
	ResponseEntity<Cart> getCarts(@PathVariable ("id") Long cartId) {
		 Cart getsingleCart = cartservice.getsingleCart(cartId);
		return new ResponseEntity<Cart>(getsingleCart, HttpStatus.OK);
	}
	
	@DeleteMapping("/cart/{cartId}")
     ResponseEntity<String> deleteCart(@PathVariable Long cartId) {
		try {
			cartservice.deleteCart(cartId);
			return ResponseEntity.ok("Cart deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(404).body(e.getMessage());
		}
	}
	
	@GetMapping("/cart/customer/{id}")
	ResponseEntity<List<Cart>> getCartsByCustomerId(@PathVariable ("id") Long cartId) {
		 List<Cart> getcartByCustomerId = cartservice.getcartByCustomerId(cartId);
		return new ResponseEntity<List<Cart>>(getcartByCustomerId, HttpStatus.OK);
	}
	
	
}
