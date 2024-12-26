package com.task.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.task.Dto.Product;
import com.task.exception.CartNotFoundException;
import com.task.model.Cart;
import com.task.repository.Cartrepository;
@Service
public class CartServiceImpl implements CartServiceI {
	@Autowired
	private Cartrepository cartrepository;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Cart createCart(Cart cart) {
		Cart creatcart = cartrepository.save(cart);
		return creatcart;
	}

	@Override
	public List<Cart> getAllCart() {
		List<Cart> list = cartrepository.findAll().stream().map(a -> a).collect(Collectors.toList());
		return list;
	}

	@Override
	public Cart updateCart(Cart cart, Long cartId) {
		Cart byId = cartrepository.findById(cartId).orElseThrow(()-> new RuntimeException("Cart Id not found"+cartId));
		byId.setQuantity(cart.getQuantity());
		byId.setIsAdded(cart.getIsAdded());
		Cart updateCart = cartrepository.save(byId);
		return updateCart;
	}

	@Override
	public Cart getsingleCart(Long cartId) {
		Cart cart = cartrepository.findById(cartId).orElseThrow(()-> new RuntimeException("Cart Id not found"+cartId));
		return cart;
	}

	@Override
    public void deleteCart(Long cartId) {
        
        if (!cartrepository.existsById(cartId)) {
            throw new CartNotFoundException("Cart not found with id: " + cartId);  
        }
        cartrepository.deleteById(cartId);
    }

	@Override
	public List<Cart> getcartByCustomerId(Long custId) {
		List<Cart> byCustomerId = cartrepository.findByCustomerId(custId);
		List<Cart> cartlist = new ArrayList<>();
		for(Cart l:byCustomerId) {    //http://localhost:9092/api/products/101
				if (l.getPid() != null) {
					String url = "http://localhost:9092/api/products/" + l.getPid();
					Product Productlist = restTemplate.getForObject(url, Product.class);
					l.setProduct(Productlist);
					cartlist.add(l);
			}
		}
		return cartlist;
	}

}
