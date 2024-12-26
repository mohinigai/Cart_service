package com.task.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.task.Dto.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Cart_dtl")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CART_ID")
	private Long cartId;

	@Column(name = "CUSTOMER_ID")
	private Long customerId;

	@Column(name = "PRODUCTT_ID")
	private Long pid;

	@Column(name = "PRODUCT_QUATITY")
	private Integer quantity;

	@Column(name = "PRODUCT_ISADDED")
	private String isAdded;

	@Column(name = "CREATE_DATE", updatable = false)
	@CreationTimestamp
	private LocalDate createDate;

	@Column(name = "UPDATE_DATE", insertable = false)
	@UpdateTimestamp
	private LocalDate updateDate;

	@Transient
	private Product product;

//    @Transient
//    private Inventory inventory;

}
