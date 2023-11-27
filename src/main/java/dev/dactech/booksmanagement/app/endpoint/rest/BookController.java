package dev.dactech.booksmanagement.app.endpoint.rest;

import dev.dactech.booksmanagement.domain.book.dto.request.BookCreationReq;
import dev.dactech.booksmanagement.domain.book.dto.request.BookUpdateReq;
import dev.dactech.booksmanagement.domain.book.dto.response.BookDetailsRes;
import dev.dactech.booksmanagement.domain.book.dto.response.BooksRes;
import dev.dactech.booksmanagement.domain.book.dto.response.ExportRes;
import dev.dactech.booksmanagement.domain.book.service.BookService;
import dev.dactech.booksmanagement.infrastructure.dto.response.Response;
import dev.dactech.booksmanagement.infrastructure.dto.response.ResponseList;
import dev.dactech.booksmanagement.infrastructure.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static dev.dactech.booksmanagement.app.service.ResponseFactory.response;
@RestController
@RequestMapping(value = "/api/books")
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody BookCreationReq req) throws ApiException {
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
            @RequestParam(name = "sort_by", required = false) String sortBy,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
            ){
        List<BooksRes> books = bookService.getAll(title, categoryId, authors, dateAdded, librarianId, deleted, sortBy, page, size);
        return response(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<BookDetailsRes>> getById(@PathVariable(name = "id") Integer id){
        BookDetailsRes bookDetailsRes = bookService.getBookDetails(id);
        return response(bookDetailsRes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable(name = "id") Integer id, @RequestBody BookUpdateReq req){
        req.setId(id);
        return response(bookService.updateBook(req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(name = "id") Integer id){
        return response(bookService.deleteBook(id));
    }

    @GetMapping("/export-inventory")
    public ResponseEntity<Response<ExportRes>> exportInventory() throws IOException {
        return response(bookService.exportInventory());
    }
}
