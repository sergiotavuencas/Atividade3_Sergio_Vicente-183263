package com.atividade03.atividade_03.controller;

import java.util.List;

import com.atividade03.atividade_03.entity.Author;
import com.atividade03.atividade_03.entity.Book;
import com.atividade03.atividade_03.service.AuthorService;
import com.atividade03.atividade_03.service.BookService;
import com.atividade03.atividade_03.service.PublisherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // Gets
    @GetMapping("/template")
    public ModelAndView getTemplate() {
        ModelAndView mv = new ModelAndView("bookTemplate");
        
        mv.addObject("book", new Book());
        mv.addObject("books", bookService.getAllBooks());
        mv.addObject("publishers", publisherService.getAllPublishers());
        return mv;
    }

    @GetMapping("/details/{id}")
    public ModelAndView getBook(@PathVariable(name = "id") Integer id) {
        ModelAndView mv = new ModelAndView("bookDetails");
        Book book = bookService.getBookById(id);
        mv.addObject("book", book);

        List<Author> authorsNotLinked = authorService.getAllAuthors();
        authorsNotLinked.removeAll(book.getAuthors());
        
        mv.addObject("authors", authorsNotLinked);
        return mv;
    }
    
    // Posts
    @PostMapping("/register")
    public String saveBook(@Validated Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "registerError";
        }
        bookService.saveBook(book);
        return "redirect:/book/template";
    }

    @PostMapping("/linkAuthor")
    public String linkAuthor(@ModelAttribute Author author, @RequestParam Integer book_id) {
        Book book = bookService.getBookById(book_id);
        author = authorService.getAuthorById(author.getId());

        book.getAuthors().add(author);
        bookService.saveBook(book);

        return "redirect:/book/details/" + book_id;
    }
}