package com.example.bookstore.repository;

import com.example.bookstore.entity.CartItem;
import com.example.bookstore.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
List<CartItem> findByCustomer(Customer customer);
void deleteByCustomer(Customer customer);
}