package com.atividade03.atividade_03.controller;

import com.atividade03.atividade_03.entity.Book;
import com.atividade03.atividade_03.service.AuthorService;
import com.atividade03.atividade_03.service.BookService;
import com.atividade03.atividade_03.service.PublisherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ModelAndView getTemplate() {
        ModelAndView mv = new ModelAndView("bookTemplate");
        
        mv.addObject("book", new Book());
        mv.addObject("books", bookService.getAllBooks());
        mv.addObject("publishers", publisherService.getAllPublishers());
        return mv;
    }
    
    @PostMapping("/register")
    public String saveBook(@Validated Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "registerError";
        }
        bookService.createBook(book);
        return "redirect:/book/template";
    }

    @GetMapping("/details/{id}")
    public ModelAndView getBook(@PathVariable(name = "id") Integer id) {
        ModelAndView mv = new ModelAndView("bookDetails");
        Book book = bookService.getBookById(id);
        mv.addObject("book", book);
        mv.addObject("authors", authorService.getAllAuthors());
        return mv;
    }
}