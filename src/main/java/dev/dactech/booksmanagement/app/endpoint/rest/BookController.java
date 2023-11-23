package dev.dactech.booksmanagement.app.endpoint.rest;

import dev.dactech.booksmanagement.domain.book.dto.request.BookCreationReq;
import dev.dactech.booksmanagement.domain.book.entity.Book;
import dev.dactech.booksmanagement.domain.book.service.BookService;
import dev.dactech.booksmanagement.infrastructure.exception.ApiException;
import dev.dactech.booksmanagement.infrastructure.dto.response.ResponseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static dev.dactech.booksmanagement.app.service.ResponseFactory.response;
@RestController
@RequestMapping(value = "/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody BookCreationReq req) throws ApiException {
        System.out.println(req);
        return response(bookService.add(req));
    }
    @GetMapping("")
    public ResponseEntity<ResponseList<Book>> getAll(){
        List<Book> books = bookService.getAll();
        return response(books);
    }
}
