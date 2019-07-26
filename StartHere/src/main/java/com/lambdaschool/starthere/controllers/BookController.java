package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> listAllBooks(HttpServletRequest request){
        List<Book> allBooks = bookService.findAll();
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    @PutMapping(value = "/data/books{id}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> updateBook(@PathVariable long id, @RequestBody Book updateBook) {
        bookService.update(updateBook, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/data/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id) {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

//    @PostMapping(value = "/data/books/{bookid}/authors/{authorid}", consumes = {"application/json"})
}
