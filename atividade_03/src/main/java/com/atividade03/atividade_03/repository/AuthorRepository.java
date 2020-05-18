package com.atividade03.atividade_03.repository;

import com.atividade03.atividade_03.entity.Author;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository <Author, Integer> {
}