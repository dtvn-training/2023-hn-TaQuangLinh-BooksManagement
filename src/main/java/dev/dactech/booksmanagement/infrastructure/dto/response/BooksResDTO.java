package dev.dactech.booksmanagement.infrastructure.dto.response;

import lombok.Builder;

@Builder
public class BooksResDTO {
    private Integer id;
    private String title;
    private String category;
    private String authors;
    private int quantity;
    private String image;
}
