package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)


private Long id;
@ManyToOne
private Customer customer;
@ManyToOne
private Book book;


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public Customer getCustomer() {
	return customer;
}


public void setCustomer(Customer customer) {
	this.customer = customer;
}


public Book getBook() {
	return book;
}


public void setBook(Book book) {
	this.book = book;
}


public Integer getQuantity() {
	return quantity;
}


public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}


private Integer quantity;


public static Object builder() 
{
	
	return null;
}
}