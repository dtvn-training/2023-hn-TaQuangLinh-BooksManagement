package dev.dactech.booksmanagement.app.service;

import dev.dactech.booksmanagement.infrastructure.dto.response.ResponseBase;
import dev.dactech.booksmanagement.infrastructure.utilies.MessageCode;
import org.springframework.http.ResponseEntity;

import static dev.dactech.booksmanagement.infrastructure.utilies.MessageCode.SUCCESS;

public class ResponseFactory {

    public static <T> ResponseEntity<ResponseBase<T>> response(MessageCode messageCode, T body){
        ResponseBase response = ResponseBase.builder()
                .messageCode(messageCode)
                .body(body)
                .build();
        return ResponseEntity.ok(response);
    }
    public static ResponseEntity<ResponseBase<Object>> response(MessageCode messageCode){
        return response(messageCode, null);
    }
    public static <T> ResponseEntity<ResponseBase<T>> response(T body){
        return response(SUCCESS, body);
    }
    public static ResponseEntity<ResponseBase<Object>> creationResponse(){
        return response(SUCCESS);
    }


}
