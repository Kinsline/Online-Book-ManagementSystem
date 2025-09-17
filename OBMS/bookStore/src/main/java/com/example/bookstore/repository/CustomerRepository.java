package com.example.bookstore.repository;

import com.example.bookstore.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;


@Repository
        public interface CustomerRepository extends JpaRepository<Customer, Long>  {Optional<Customer> findByEmail(String email);
}