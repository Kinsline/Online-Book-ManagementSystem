package com.example.bookstore.controller;

import com.example.bookstore.entity.CartItem;
import com.example.bookstore.entity.Customer;
import com.example.bookstore.entity.Order;
import com.example.bookstore.repository.CartItemRepository;
import com.example.bookstore.service.CustomerService;
import com.example.bookstore.service.OrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;


@Controller
		public class OrderController {
		private final CartItemRepository cartItemRepository;
		private final CustomerService customerService;
		private final OrderService orderService;
		
	public OrderController(CartItemRepository cartItemRepository,CustomerService customerService, OrderService orderService) 
		{
		this.cartItemRepository = cartItemRepository;
		this.customerService = customerService;
		this.orderService = orderService;
		}

@GetMapping("/checkout")
			public String checkout(@AuthenticationPrincipal UserDetails user, Model model)
			{
				if (user == null) 
					return "redirect:/login";
			Customer c = customerService.findByEmail(user.getUsername());
			List<CartItem> items = cartItemRepository.findByCustomer(c);
			model.addAttribute("items", items);
				return "checkout";
			}

@SuppressWarnings("unused")
@PostMapping("/checkout")
		public String doCheckout(@AuthenticationPrincipal UserDetails user)
			{
				if (user == null) return "redirect:/login";
					Customer c = customerService.findByEmail(user.getUsername());
					List<CartItem> items = cartItemRepository.findByCustomer(c);
					
						if (items.isEmpty()) return "redirect:/cart/view";
						Order order = orderService.createOrder(c, items);
						// simulate payment success; in production integrate Stripe/PayPal
							cartItemRepository.deleteByCustomer(c);
									return "redirect:/books";
					}
	}