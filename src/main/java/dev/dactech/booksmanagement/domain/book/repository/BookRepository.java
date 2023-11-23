package dev.dactech.booksmanagement.domain.book.repository;

import dev.dactech.booksmanagement.domain.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, BookRepositoryCustom {
}
