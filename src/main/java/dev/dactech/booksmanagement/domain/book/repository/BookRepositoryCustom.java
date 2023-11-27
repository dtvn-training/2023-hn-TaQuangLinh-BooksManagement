package dev.dactech.booksmanagement.domain.book.repository;

import dev.dactech.booksmanagement.domain.book.entity.Book;

import java.util.List;

public interface BookRepositoryCustom {
    public List<Object[]> getInventoryBook();
    public List<Object[]> getOverdue();
}
