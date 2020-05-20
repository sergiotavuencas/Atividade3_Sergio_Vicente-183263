package com.atividade03.atividade_03.controller;

import com.atividade03.atividade_03.entity.Book;
import com.atividade03.atividade_03.service.AuthorService;
import com.atividade03.atividade_03.service.BookService;
import com.atividade03.atividade_03.service.PublisherService;

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

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PublisherService publisherService;

    @GetMapping("/template")
    public ModelAndView getAuthors() {
        ModelAndView mv = new ModelAndView("bookTemplate");
        
        mv.addObject("book", new Book());
        mv.addObject("books", bookService.getAllBooks());
        mv.addObject("publishers", publisherService.getAllPublishers());
        //mv.addObject("authors", authorService.getAllAuthors());
        return mv;
    }
    
    @PostMapping("/register")
    public String saveBook(@ModelAttribute Book book) {
        bookService.createBook(book);
        return "redirect:/book/template";
    }
}