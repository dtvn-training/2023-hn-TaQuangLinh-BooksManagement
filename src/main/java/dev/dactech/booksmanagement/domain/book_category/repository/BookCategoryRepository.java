package dev.dactech.booksmanagement.domain.book_category.repository;

import dev.dactech.booksmanagement.domain.book_category.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Integer> {
}
