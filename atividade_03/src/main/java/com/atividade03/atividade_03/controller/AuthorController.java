package com.atividade03.atividade_03.controller;

import java.util.List;

import com.atividade03.atividade_03.entity.Author;
import com.atividade03.atividade_03.entity.Book;
import com.atividade03.atividade_03.service.AuthorService;
import com.atividade03.atividade_03.service.BookService;

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
@RequestMapping("/author")
public class AuthorController {
    
    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    // Gets
    @GetMapping("/template")
    public ModelAndView getTemplate() {
        ModelAndView mv = new ModelAndView("authorTemplate");
        mv.addObject("author", new Author());
        mv.addObject("authors", authorService.getAllAuthors());
        return mv;
    }

    @GetMapping("/details/{id}")
    public ModelAndView getAuthor(@PathVariable(name = "id") Integer id) {
        ModelAndView mv = new ModelAndView("authorDetails");
        Author author = authorService.getAuthorById(id);
        mv.addObject("author", author);

        List<Book> booksNotLinked = bookService.getAllBooks();
        booksNotLinked.removeAll(author.getBooks());
        
        mv.addObject("books", booksNotLinked);
        return mv;
    }

    // Posts
    @PostMapping("/register")
    public String saveAuthor(@Validated Author author, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "registerError";
        }
        authorService.saveAuthor(author);
        return "redirect:/author/template";
    }

    @PostMapping("/linkBook")
    public String linkBook(@ModelAttribute Book book, @RequestParam Integer author_id) {
        Author author = authorService.getAuthorById(author_id);
        book = bookService.getBookById(book.getId());

        author.getBooks().add(book);
        authorService.saveAuthor(author);

        return "redirect:/author/details/" + author_id;
    }
}