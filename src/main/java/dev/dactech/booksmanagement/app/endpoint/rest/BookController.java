package dev.dactech.booksmanagement.app.endpoint.rest;

import dev.dactech.booksmanagement.domain.book.dto.request.BookCreationReq;
import dev.dactech.booksmanagement.domain.book.service.BookService;
import dev.dactech.booksmanagement.infrastructure.dto.response.ResponseBase;
import dev.dactech.booksmanagement.infrastructure.utilies.MessageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static dev.dactech.booksmanagement.app.service.ResponseFactory.creationResponse;
import static dev.dactech.booksmanagement.app.service.ResponseFactory.response;

@RestController
@RequestMapping("/api/books/")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<ResponseBase> add(@RequestBody BookCreationReq req){
        if (bookService.add(req)){
            return creationResponse();
        }else
            return response(MessageCode.ERROR_BOOK_CREATION);
    }
}
