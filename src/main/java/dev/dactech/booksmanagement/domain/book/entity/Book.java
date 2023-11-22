package dev.dactech.booksmanagement.domain.book.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Column(name = "authors")
    private String authors;

    @Column(name = "publishing_date")
    private LocalDateTime publishingDate;

    @Column(nullable = false)
    private int quantity;

    @CreationTimestamp
    @Column(name = "date_added", nullable = false)
    private LocalDateTime dateAdded;

    @Column(name = "librarian_id", nullable = false)
    private Integer librarianId;

    @Column
    private String image;

    @Column(name = "limit_date", nullable = false)
    private int limitDate;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

}
