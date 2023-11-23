package dev.dactech.booksmanagement.domain.book.repository;

import dev.dactech.booksmanagement.infrastructure.dto.response.BooksResDTO;

import java.util.List;
public interface BookRepositoryCustom {
    public List<BooksResDTO> getAll(String title, Integer categoryId, String authors, String dateAdded, Integer librarianId, Integer deleted, String sortBy);
}
