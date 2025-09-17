package com.example.bookstore.service;

import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BookService {
    private final BookRepository bookRepository;

        public BookService(BookRepository bookRepository) 
            {
            this.bookRepository = bookRepository;
            }

        public List<Book> listAll()     
            {
            return bookRepository.findAll(); 
            }

        public Book get(Long id)
            { 
            return bookRepository.findById(id).orElse(null);
            }

         public List<Book> search(String q) 
            {
            return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(q, q);
            }
 }