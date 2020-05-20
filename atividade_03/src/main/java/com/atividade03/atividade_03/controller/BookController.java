package com.atividade03.atividade_03.controller;

import com.atividade03.atividade_03.entity.Book;
import com.atividade03.atividade_03.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/book")
public class BookController {
    
    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public ModelAndView getAuthors() {
        ModelAndView mv = new ModelAndView("bookHome");
        mv.addObject("books", bookService.getAllBooks());
        return mv;
    }

    @GetMapping("/register")
    public String registerForm() {
        return "bookRegister";
    }

    @PostMapping("/register")
    public String saveBook(@ModelAttribute Book book) {
        bookService.createBook(book);
        return "redirect:/book/all";
    }
}