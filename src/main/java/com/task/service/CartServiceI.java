package com.task.service;

import java.util.List;

import com.task.model.Cart;

public interface CartServiceI {
	
	public Cart createCart(Cart cart);
	
	public List<Cart> getAllCart();
	
	public Cart updateCart(Cart cart, Long cartId);
	
	public Cart getsingleCart(Long cartId);
	
	public void deleteCart(Long cartId);
	
	public List<Cart> getcartByCustomerId(Long custId);

}
