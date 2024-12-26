package com.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import com.task.model.Cart;


@RestController
public interface Cartrepository extends JpaRepository<Cart, Long> {
	
	List<Cart>findByCustomerId(Long custId);
	
	//Cart findBypid(Long pid);
}
