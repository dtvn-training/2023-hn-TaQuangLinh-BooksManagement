package dev.dactech.booksmanagement.infrastructure.utilies;

import lombok.Getter;

@Getter
public enum MessageCode {
    DUPLICATE (400, "DUPLICATE", "Loi..."),
    EXIST(499, "EXIST", "Tai khoan da ton tai"),
    SUCCESS(200, "SUCCESS", "Thành công"),
    FAIL(401, "FAIL", "Thất bại"),
    ERROR_BOOK_CREATION(402, "ERROR_CREATION", "Thêm mới book không thành công"),
    MISSING_CATEGORY_FIELD(403, "MISSING_CATEGORY_FIELD", "Không được để trống trường category"),
    MISSING_QUANTITY_FIELD(405, "MISSING_QUANTITY_FIELD", "Không được để trống trường quantity"),
    MISSING_LIBRARIAN_FIELD(406, "MISSING_LIBRARIAN_FIELD", "Không được để trống trường librarian"),
    MISSING_LIMIT_DATE_FIELD(407, "MISSING_LIMIT_DATE_FIELD", "Không được để trống trường limit date"),
    MISSING_TITLE_FIELD(408, "MISSING_TITLE_FIELD", "Không được để trống trường title"),
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
