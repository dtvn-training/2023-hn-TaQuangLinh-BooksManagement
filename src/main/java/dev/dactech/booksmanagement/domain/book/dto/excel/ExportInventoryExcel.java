package dev.dactech.booksmanagement.domain.book.dto.excel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExportInventoryExcel {
    private Integer id;
    private String title;
    private String category;
    private String authors;
    private Long quantity;
    private String image;
    private String dateAdded;
}
