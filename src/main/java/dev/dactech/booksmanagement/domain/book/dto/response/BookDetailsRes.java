package dev.dactech.booksmanagement.domain.book.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookDetailsRes {
    private Integer id;
    private String title;
    private String category;
    private String authors;
    private String publishingDate;
    private Integer quantity;
    private String librarian;
    private String image;
    private Integer limitDate;
    private String deletedAt;
    private String dateAdded;
}
