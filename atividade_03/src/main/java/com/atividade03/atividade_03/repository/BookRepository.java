package com.atividade03.atividade_03.repository;

import com.atividade03.atividade_03.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <Book, Integer>{
}