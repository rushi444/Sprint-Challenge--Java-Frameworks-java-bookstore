package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.ErrorDetail;
import com.lambdaschool.starthere.services.BookService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @ApiOperation(value = "Return all Books", response = Book.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})

    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> listAllBooks(HttpServletRequest request, @PageableDefault(page = 0, size = 5) Pageable pageable){

        List<Book> allBooks = bookService.findAll(pageable);
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }
    @ApiOperation(value = "Update a current Book", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully updated book", response = void.class),
            @ApiResponse(code = 500, message = "Failed to update book", response = ErrorDetail.class)
    })

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

    @PostMapping(value = "/data/books/{bookid}/authors/{authorid}", consumes = {"application/json"})
    public ResponseEntity<?>bookWithAuthor(@PathVariable long bookid, @PathVariable long authorid){
        bookService.assignAuthor(bookid, authorid);
        return new ResponseEntity<>(bookService.findAll(Pageable.unpaged()), HttpStatus.OK);
    }
}
