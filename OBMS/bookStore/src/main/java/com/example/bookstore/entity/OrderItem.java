package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Order order; // <-- mapping back to Order

    private Integer quantity;

    private Double price; // price at time of purchase
}