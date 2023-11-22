package dev.dactech.booksmanagement.domain.book.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomBookRepository {
    public List<Object> getAllBooksDetail();
}
