package com.example.bookstore.controller;

import com.example.bookstore.entity.Customer;
import com.example.bookstore.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
    public class AuthController {
		private final CustomerService customerService;
			public AuthController(CustomerService customerService)
			    {
			    this.customerService = customerService;
			    }

@GetMapping("/login")
        public String login()
            {
             return "login";
             }

@GetMapping("/register")
        public String registerForm(Model model)
         {
                model.addAttribute("customer", new Customer());
                return "register";
        }

@PostMapping("/register")
            public String register( @ModelAttribute Customer customer)
             {
                customerService.register(customer);
                    return "redirect:/login";
            }
}
