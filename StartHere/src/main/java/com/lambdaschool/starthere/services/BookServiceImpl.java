package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.AuthorRepository;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookrepos;

    @Autowired
    AuthorRepository authorRepo;

    @Override
    public List<Book> findAll(Pageable pageable) {
        List<Book> list = new ArrayList<>();
        bookrepos.findAll(pageable).iterator().forEachRemaining(list::add);
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
        if(updateBook.getAuthorList() != null && updateBook.getAuthorList().size() > 0) {
            newBook.setAuthorList(newBook.getAuthorList());
        }

        return bookrepos.save(newBook);
    }

    @Transactional
    @Override
    public void assignAuthor(long bookid, long authorid){
        Book newBook = bookrepos.findById(bookid).orElseThrow(EntityNotFoundException::new);
        newBook.getAuthorList().add(authorRepo.findById(authorid).orElseThrow(EntityNotFoundException::new));
    }

}
