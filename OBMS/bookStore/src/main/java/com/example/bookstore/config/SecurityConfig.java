package com.example.bookstore.config;

import com.example.bookstore.entity.Customer;
import com.example.bookstore.repository.CustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomerRepository customerRepository;

    public SecurityConfig(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Optional<Customer> c = customerRepository.findByEmail(username);
            if (c.isPresent()) {
                Customer customer = c.get();
                UserDetails user = User.withUsername(customer.getEmail())
                        .password(customer.getPassword())
                        .roles("USER")
                        .build();
                return user;
            }
           
            return User.withDefaultPasswordEncoder()
                    .username("admin")
                    .password("admin")
                    .roles("ADMIN")
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/js/**", "/images/**", "/register", "/login", "/books", "/")
                .permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/books", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/books")
            );
        return http.build();
    }
}