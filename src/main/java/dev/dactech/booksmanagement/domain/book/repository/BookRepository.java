package dev.dactech.booksmanagement.domain.book.repository;

import dev.dactech.booksmanagement.domain.book.entity.Book;
import dev.dactech.booksmanagement.domain.book.service.BookSpecification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, BookRepositoryCustom {
    @Query(value = "select books.* from books, book_category where " +
            "books.category_id = book_category.id and " +
            "(:title is null or title like %:title%) and " +
            "(:categoryId is null or category_id = :categoryId) and " +
            "(:authors is null or authors like %:authors%) and " +
            "(:dateAdded is null or DATE(date_added) = :dateAdded) and " +
            "(:librarianId is null or librarian_id = :librarianId) and " +
            "((:deleted = 0 or deleted_at is null) and (:deleted = 1 or deleted_at is null)) "
            , nativeQuery = true)
    public List<Book> getAll(String title, Integer categoryId, String authors, LocalDate dateAdded, Integer librarianId, Integer deleted, Pageable pageable);


}
