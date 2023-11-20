package dev.dactech.booksmanagement.infrastructure.dto.response;

import dev.dactech.booksmanagement.infrastructure.utilies.MessageCode;
import lombok.*;
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBase<T> {
    private MessageCode messageCode;
    private T body;

    public void setMessageCode(MessageCode messageCode) {
        this.messageCode = messageCode;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
