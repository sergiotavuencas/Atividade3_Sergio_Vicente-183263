package com.atividade03.atividade_03.controller;

import com.atividade03.atividade_03.entity.Author;
import com.atividade03.atividade_03.service.AuthorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/author")
public class AuthorController {
    
    @Autowired
    private AuthorService authorService;

    @GetMapping("/template")
    public ModelAndView getTemplate() {
        ModelAndView mv = new ModelAndView("authorTemplate");
        mv.addObject("author", new Author());
        mv.addObject("authors", authorService.getAllAuthors());
        return mv;
    }

    @PostMapping("/register")
    public String saveAuthor(@Validated Author author, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "registerError";
        }
        authorService.createAuthor(author);
        return "redirect:/author/template";
    }
}