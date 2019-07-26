package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookrepos;

    @Override
    public List<Book> findAll() {
        List<Book> list = new ArrayList<>();
        bookrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Book findBookById(long id) {
        return bookrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

    }

    @Override
    public void delete(long id) {
        bookrepos.deleteById(id);

    }

    @Override
    public Book save(Book book) {
        return bookrepos.save(book);
    }
}
