package dev.dactech.booksmanagement.domain.book.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExportRes {
    private String link;
}
