package dev.dactech.booksmanagement.domain.book.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BooksRes {
    private Integer id;
    private String title;
    private String category;
    private String authors;
    private int quantity;
    private String image;
}
