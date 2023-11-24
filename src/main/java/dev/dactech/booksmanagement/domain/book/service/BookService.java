package dev.dactech.booksmanagement.domain.book.service;

import dev.dactech.booksmanagement.domain.book.dto.request.BookCreationReq;
import dev.dactech.booksmanagement.domain.book.dto.response.BooksRes;
import dev.dactech.booksmanagement.infrastructure.utilies.MessageCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    public MessageCode add(BookCreationReq req);

    public List<BooksRes> getAll(String title, Integer categoryId, String authors, String dateAdded, Integer librarianId, Integer deleted, String sortBy);
}
