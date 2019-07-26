package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
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

    @Transactional
    @Override
    public Book update(Book updateBook, long id) {
        Book newBook = bookrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if(updateBook.getBooktitle() != null) {
            newBook.setBooktitle(updateBook.getBooktitle());
        }
        if(updateBook.getIsbn() != null) {
            newBook.setIsbn(updateBook.getIsbn());
        }
        if(updateBook.getCopy() != null) {
            newBook.setCopy(updateBook.getCopy());
        }

        return bookrepos.save(newBook);
    }
}
