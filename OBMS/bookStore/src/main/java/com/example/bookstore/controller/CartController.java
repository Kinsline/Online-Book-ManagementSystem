package com.example.bookstore.controller;

import com.example.bookstore.entity.CartItem;
import com.example.bookstore.entity.Customer;
import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CartItemRepository;
import com.example.bookstore.service.CustomerService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/cart")
    public class CartController {
    private final BookRepository bookRepository;
    private final CartItemRepository cartItemRepository;
    private final CustomerService customerService;

            public CartController(BookRepository bookRepository, CartItemRepository cartItemRepository, CustomerService customerService)
             {
                 this.bookRepository = bookRepository;
                 this.cartItemRepository = cartItemRepository;
                 this.customerService = customerService;
            }

@PostMapping("/add")
            public String addToCart(@RequestParam Long bookId,@RequestParam(defaultValue = "1") Integer qty, @AuthenticationPrincipal UserDetails user) 
            {
                if (user == null) return "redirect:/login";
                    Customer c = customerService.findByEmail(user.getUsername());
                    Book b = bookRepository.findById(bookId).orElse(null);
                 if (b == null) 
                    return "redirect:/books";
                    
                        CartItem item =(CartItem.builder().customer(c).book(b).quantity(qty).build());
                            cartItemRepository.save(item);
                        return "redirect:/cart/view";
            }

@GetMapping("/view")
            public String viewCart(@AuthenticationPrincipal UserDetails user, Model model)
                 {
                     if (user == null) return "redirect:/login";
                        Customer c = customerService.findByEmail(user.getUsername());
                        List<CartItem> items = cartItemRepository.findByCustomer(c);
                        model.addAttribute("items", items);
                    return "cart";
                }

@PostMapping("/clear")
            public String clearCart(@AuthenticationPrincipal UserDetails user)
             {
                if (user == null) return "redirect:/login";
                    Customer c = customerService.findByEmail(user.getUsername());
                    cartItemRepository.deleteByCustomer(c);
                    return "redirect:/cart/view";
             }
}
