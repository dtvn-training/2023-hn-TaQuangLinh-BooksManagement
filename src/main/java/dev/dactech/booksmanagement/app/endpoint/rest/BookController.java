package dev.dactech.booksmanagement.app.endpoint.rest;

import dev.dactech.booksmanagement.domain.book.dto.request.BookCreationReq;
import dev.dactech.booksmanagement.domain.book.dto.response.BooksRes;
import dev.dactech.booksmanagement.domain.book.entity.Book;
import dev.dactech.booksmanagement.domain.book.service.BookService;
import dev.dactech.booksmanagement.infrastructure.dto.response.ResponseList;
import dev.dactech.booksmanagement.infrastructure.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<ResponseList<BooksRes>> getAll(
            @RequestParam(required = false) String title,
            @RequestParam(name = "category_id", required = false) Integer categoryId,
            @RequestParam(required = false) String authors,
            @RequestParam(name = "date_added", required = false) String dateAdded,
            @RequestParam(name = "librarian_id", required = false) Integer librarianId,
            @RequestParam(name = "delete", required = false) Integer deleted,
            @RequestParam(name = "sort_by", required = false) String sortBy
            ){
        List<BooksRes> books = bookService.getAll(title, categoryId, authors, dateAdded, librarianId, deleted, sortBy);
        return response(books);
    }
}
