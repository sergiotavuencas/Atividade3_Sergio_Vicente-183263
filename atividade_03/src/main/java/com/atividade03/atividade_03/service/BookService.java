package com.atividade03.atividade_03.service;

import java.util.List;

import com.atividade03.atividade_03.entity.Book;
import com.atividade03.atividade_03.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    
    @Autowired
    BookRepository bookRepository;

    public void createBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}