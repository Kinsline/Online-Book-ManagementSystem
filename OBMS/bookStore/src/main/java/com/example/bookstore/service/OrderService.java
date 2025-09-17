package com.example.bookstore.service;

import com.example.bookstore.entity.*;
import com.example.bookstore.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Customer customer, List<CartItem> cartItems) {
        // Build Order
        Order order = Order.builder()
                .customer(customer)
                .createdAt(LocalDateTime.now())
                .status("PENDING")
                .build();

        // Convert CartItems -> OrderItems
        List<OrderItem> items = cartItems.stream()
                .map(ci -> OrderItem.builder()
                        .book(ci.getBook())
                        .quantity(ci.getQuantity())
                        .price(ci.getBook().getPrice())
                        .order(order)   // associate order
                        .build())
                .collect(Collectors.toList());

        // Attach items & calculate total
        order.setItems(items);
        order.setTotal(
                items.stream()
                        .mapToDouble(i -> i.getPrice() * i.getQuantity())
                        .sum()
        );

        // Save order
        return orderRepository.save(order);
    }
}