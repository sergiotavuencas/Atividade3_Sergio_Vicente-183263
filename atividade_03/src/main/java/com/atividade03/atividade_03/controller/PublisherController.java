package com.atividade03.atividade_03.controller;

import com.atividade03.atividade_03.entity.Publisher;
import com.atividade03.atividade_03.service.PublisherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/publisher")
public class PublisherController {
    
    @Autowired
    private PublisherService publisherService;

    @GetMapping("/template")
    public ModelAndView getAuthors() {
        ModelAndView mv = new ModelAndView("publisherTemplate");
        mv.addObject("publisher", new Publisher());
        mv.addObject("publishers", publisherService.getAllPublishers());
        return mv;
    }

    @PostMapping("/register")
    public String savePublisher (@ModelAttribute Publisher publisher) {
        publisherService.createPublisher(publisher);
        return "redirect:/publisher/template";
    }
}