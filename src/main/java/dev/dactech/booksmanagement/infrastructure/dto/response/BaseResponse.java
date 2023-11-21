package dev.dactech.booksmanagement.infrastructure.dto.response;

import dev.dactech.booksmanagement.infrastructure.utilies.MessageCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private int code;
    private String message;
    private String description;

    public BaseResponse(MessageCode messageCode){
        this.code = messageCode.getCode();
        this.message = messageCode.getMessage();
        this.description = messageCode.getDescription();
    }
    public void setMessageCode(MessageCode messageCode){
        this.code = messageCode.getCode();
        this.message = messageCode.getMessage();
        this.description = messageCode.getDescription();
    }
}
