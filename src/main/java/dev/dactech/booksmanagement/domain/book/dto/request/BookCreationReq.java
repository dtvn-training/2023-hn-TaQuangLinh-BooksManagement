package dev.dactech.booksmanagement.domain.book.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCreationReq {
    private String title;
    private String categoryName;
    private Integer categoryId;
    private String authors;
    private LocalDate publishingDate;
    private Integer quantity;
    private Integer librarianId;
    private Integer limitDate;
    private String image;
}
