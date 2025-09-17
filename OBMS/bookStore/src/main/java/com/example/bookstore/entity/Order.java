package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@ManyToOne
private Customer customer;


private LocalDateTime createdAt;
private Double total;
private String status;


@OneToMany(cascade = CascadeType.ALL)
private List<OrderItem> items;


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


public LocalDateTime getCreatedAt() {
	return createdAt;
}


public void setCreatedAt(LocalDateTime createdAt) {
	this.createdAt = createdAt;
}


public Double getTotal() {
	return total;
}


public void setTotal(Double total) {
	this.total = total;
}


public String getStatus() {
	return status;
}


public void setStatus(String status) {
	this.status = status;
}


public List<OrderItem> getItems() {
	return items;
}


public void setItems(List<OrderItem> items) {
	this.items = items;
}


}