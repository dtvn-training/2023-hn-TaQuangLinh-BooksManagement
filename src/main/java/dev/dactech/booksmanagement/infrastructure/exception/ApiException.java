package dev.dactech.booksmanagement.infrastructure.exception;

import dev.dactech.booksmanagement.infrastructure.dto.response.BaseResponse;
import dev.dactech.booksmanagement.infrastructure.utilies.MessageCode;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiException extends RuntimeException{
    private int code;
    private String message;
    private String description;
    private Object data;

    public ApiException(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }
    public ApiException(MessageCode messageCode){
        this.code = messageCode.getCode();
        this.message = messageCode.getMessage();
        this.description = messageCode.getDescription();
    }
    public ApiException(MessageCode messageCode, Object data){
        this.code = messageCode.getCode();
        this.message = messageCode.getMessage();
        this.description = messageCode.getDescription();
        this.data = data;
    }
    public void setMessageCode(MessageCode messageCode){
        this.code = messageCode.getCode();
        this.message = messageCode.getMessage();
        this.description = messageCode.getDescription();
    }
    public void setMessageCode(MessageCode messageCode, Object data){
        setMessageCode(messageCode);
        this.data = data;
    }

}
