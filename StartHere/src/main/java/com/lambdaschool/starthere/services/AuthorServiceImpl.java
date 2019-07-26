package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    AuthorRepository authorrepos;

    @Override
    public List<Author> findAll() {
       List<Author> list = new ArrayList<>();
       authorrepos.findAll().iterator().forEachRemaining(list::add);
       return list;
    }

    @Override
    public Author findAuthorById(long id) {
        return authorrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id) {
        authorrepos.deleteById(id);
    }

    @Override
    public Author save(Author author) {
        return authorrepos.save(author);
    }
}
