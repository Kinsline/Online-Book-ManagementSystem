package com.example.bookstore.service;

import com.example.bookstore.entity.Customer;
import com.example.bookstore.repository.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
	public class CustomerService {
		
	private final CustomerRepository customerRepository;
	private final PasswordEncoder passwordEncoder;

		public CustomerService(CustomerRepository customerRepository,PasswordEncoder passwordEncoder)
			{
				this.customerRepository = customerRepository;
				this.passwordEncoder = passwordEncoder;
			}

		public Customer register(Customer customer)
			{
				customer.setPassword(passwordEncoder.encode(customer.getPassword()));
				return customerRepository.save(customer);
			}

		public Customer findByEmail(String email)
			{
				return customerRepository.findByEmail(email).orElse(null);
			}
}
