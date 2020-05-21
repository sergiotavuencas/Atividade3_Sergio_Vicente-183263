package com.atividade03.atividade_03.controller;

import com.atividade03.atividade_03.entity.Publisher;
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
@RequestMapping("/publisher")
public class PublisherController {
    
    @Autowired
    private PublisherService publisherService;

    @Autowired
    private BookService bookService;

    @GetMapping("/template")
    public ModelAndView getTemplate() {
        ModelAndView mv = new ModelAndView("publisherTemplate");
        mv.addObject("publisher", new Publisher());
        mv.addObject("publishers", publisherService.getAllPublishers());
        return mv;
    }

    @PostMapping("/register")
    public String savePublisher (@Validated Publisher publisher, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "registerError";
        }
        publisherService.createPublisher(publisher);
        return "redirect:/publisher/template";
    }

    @GetMapping("/details/{id}")
    public ModelAndView getPublisher(@PathVariable(name = "id") Integer id) {
        ModelAndView mv = new ModelAndView("publisherDetails");
        Publisher publisher = publisherService.getPublisherById(id);
        mv.addObject("publisher", publisher);
        mv.addObject("books", bookService.getAllBooks());
        return mv;
    }
}