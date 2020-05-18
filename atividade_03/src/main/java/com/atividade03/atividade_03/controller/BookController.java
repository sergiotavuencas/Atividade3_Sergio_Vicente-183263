package com.atividade03.atividade_03.controller;

import com.atividade03.atividade_03.entity.Book;
import com.atividade03.atividade_03.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {
    
    @Autowired
    private BookService bookService;

    @GetMapping("/register")
    public String registerForm() {
        return "registerBook";
    }

    @PostMapping("/register")
    public String saveBook(@Validated Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "redirect:/book/register";
        }
        bookService.createBook(book);
        return "index";
    }
}