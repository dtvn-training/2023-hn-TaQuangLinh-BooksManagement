package dev.dactech.booksmanagement.domain.librarian.repository;

import dev.dactech.booksmanagement.domain.librarian.entity.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {
}
