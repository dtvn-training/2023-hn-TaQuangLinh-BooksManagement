package dev.dactech.booksmanagement.domain.book.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookUpdateReq {
    private Integer id;
    private String title;
    private Integer categoryId;
    private String authors;
    private LocalDate publishingDate;
    private Integer quantity;
    private Integer librarianId;
    private Integer limitDate;
    private String image;
}
