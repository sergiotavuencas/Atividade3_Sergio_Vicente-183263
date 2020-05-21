package com.atividade03.atividade_03.service;

import java.util.List;

import com.atividade03.atividade_03.entity.Author;
import com.atividade03.atividade_03.repository.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    
    @Autowired
    AuthorRepository authorRepository;

    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    public Author getAuthorById(int id) {
        return authorRepository.findById(id).get();
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}