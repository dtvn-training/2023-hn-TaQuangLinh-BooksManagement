package dev.dactech.booksmanagement.domain.book.dto.excel;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class ExportOverdueExcel {
    private Long id;
    private String studentCode;
    private Integer bookId;
    private String bookName;
    private LocalDateTime startTime;
    private LocalDate expiredDate;
    private Long numOfDayOverdue;
}
