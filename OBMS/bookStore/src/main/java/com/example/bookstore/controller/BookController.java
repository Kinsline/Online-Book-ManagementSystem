package com.example.bookstore.controller;

import com.example.bookstore.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
        public class BookController {
	
        private final BookService bookService;
        
        public BookController(BookService bookService) 
	        {
	        this.bookService = bookService;
	        }
@GetMapping({"/", "/books"})
        public String list(@RequestParam(value = "q", required = false) String q,Model model)
         {
            model.addAttribute("books", q == null || q.isBlank() ?bookService.listAll() : bookService.search(q));
            model.addAttribute("q", q);
            return "books";
        }

@GetMapping("/books/{id}")
            public String details(@PathVariable Long id, Model model)
         {
            model.addAttribute("book", bookService.get(id));
            return "book-details";
        }
}