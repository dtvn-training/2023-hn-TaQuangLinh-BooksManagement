package dev.dactech.booksmanagement.domain.book.service;

import dev.dactech.booksmanagement.domain.book.dto.request.BookCreationReq;
import dev.dactech.booksmanagement.domain.book.dto.response.BookCreationRes;
import dev.dactech.booksmanagement.domain.book.entity.Book;
import dev.dactech.booksmanagement.domain.book.repository.BookRepository;
import dev.dactech.booksmanagement.infrastructure.dto.response.ResponseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public boolean add(BookCreationReq req){
        Book book = Book.builder()
                .name(req.getName())
                .build();
        try{
            bookRepository.save(book);
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public List<Book> getAll() {
        List<Book> books = bookRepository.findAll();
        return books;
    }
}
