package dev.dactech.booksmanagement.infrastructure.utilies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public enum MessageCode {
    DUPLICATE (400, "DUPLICATE", "Loi..."),
    EXIST(411, "EXIST", "Tai khoan da ton tai"),
    SUCCESS(200, "SUCCESS", "Thành công"),
    ERROR_BOOK_CREATION(401, "ERROR_CREATION", "Thêm mới sách không thành công"),
    ;
    private int code;
    private String message;
    private String description;

    MessageCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }
}
