package dev.dactech.booksmanagement.domain.book_student.repository;

import dev.dactech.booksmanagement.domain.book_student.entity.BookStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookStudentRepository extends JpaRepository<BookStudent, Long> {
}
