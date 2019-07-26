package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    Author findAuthorById(long id);

    void delete(long id);

    Author save(Author author);
}
