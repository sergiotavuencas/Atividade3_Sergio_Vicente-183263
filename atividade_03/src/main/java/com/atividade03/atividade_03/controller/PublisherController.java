package com.atividade03.atividade_03.controller;

import java.util.List;

import com.atividade03.atividade_03.entity.Book;
import com.atividade03.atividade_03.entity.Publisher;
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
@RequestMapping("/publisher")
public class PublisherController {
    
    @Autowired
    private PublisherService publisherService;

    @Autowired
    private BookService bookService;

    // Gets
    @GetMapping("/template")
    public ModelAndView getTemplate() {
        ModelAndView mv = new ModelAndView("publisherTemplate");
        mv.addObject("publisher", new Publisher());
        mv.addObject("publishers", publisherService.getAllPublishers());
        return mv;
    }

    @GetMapping("/details/{id}")
    public ModelAndView getPublisher(@PathVariable(name = "id") Integer id) {
        ModelAndView mv = new ModelAndView("publisherDetails");
        Publisher publisher = publisherService.getPublisherById(id);
        mv.addObject("publisher", publisher);

        List<Book> booksNotLinked = bookService.getAllBooks();
        booksNotLinked.removeAll(publisher.getBooks());
        
        mv.addObject("books", bookService.getAllBooks());
        return mv;
    }

    // Posts
    @PostMapping("/register")
    public String savePublisher (@Validated Publisher publisher, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "registerError";
        }
        publisherService.savePublisher(publisher);
        return "redirect:/publisher/template";
    }

    @PostMapping("/linkBook")
    public String linkBook(@ModelAttribute Book book, @RequestParam Integer publisher_id) {
        Publisher publisher = publisherService.getPublisherById(publisher_id);
        book = bookService.getBookById(book.getId());

        publisher.getBooks().add(book);
        book.setPublisher(publisher);
        publisherService.savePublisher(publisher);

        return "redirect:/publisher/details/" + publisher_id;
    }
}