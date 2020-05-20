package com.atividade03.atividade_03.controller;

import com.atividade03.atividade_03.entity.Author;
import com.atividade03.atividade_03.service.AuthorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/author")
public class AuthorController {
    
    @Autowired
    private AuthorService authorService;

    @GetMapping("/all")
    public ModelAndView getAuthors() {
        ModelAndView mv = new ModelAndView("authorHome");
        mv.addObject("authors", authorService.getAllAuthors());
        return mv;
    }

    @GetMapping("/register")
    public String registerForm() {
        return "authorRegister";
    }

    @PostMapping("/register")
    public String saveAuthor(@ModelAttribute Author author) {
        authorService.createAuthor(author);
        return "redirect:/author/all";
    }
}