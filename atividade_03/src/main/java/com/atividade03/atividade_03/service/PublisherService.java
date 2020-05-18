package com.atividade03.atividade_03.service;

import java.util.List;

import com.atividade03.atividade_03.entity.Publisher;
import com.atividade03.atividade_03.repository.PublisherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {
    
    @Autowired
    PublisherRepository publisherRepository;

    public void createPublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }
}