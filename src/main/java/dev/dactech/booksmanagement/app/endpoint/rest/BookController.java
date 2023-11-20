package dev.dactech.booksmanagement.app.endpoint.rest;

import dev.dactech.booksmanagement.domain.book.dto.request.BookCreationReq;
import dev.dactech.booksmanagement.domain.book.service.BookService;
import dev.dactech.booksmanagement.infrastructure.dto.response.ResponseBase;
import dev.dactech.booksmanagement.infrastructure.utilies.MessageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static dev.dactech.booksmanagement.app.service.ResponseFactory.*;
@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping()
    public ResponseEntity<ResponseBase<Object>> add(@RequestBody BookCreationReq req){
        if (bookService.add(req)){
            return creationResponse();
        }else
            return response(MessageCode.ERROR_BOOK_CREATION);
    }
    @GetMapping(value = "/books", produces = "application/json")
    public ResponseEntity<ResponseBase<String>> display(){
        return response("Hello");
    }
}
