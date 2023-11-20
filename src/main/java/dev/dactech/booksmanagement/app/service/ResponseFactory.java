package dev.dactech.booksmanagement.app.service;

import dev.dactech.booksmanagement.infrastructure.dto.response.ResponseBase;
import dev.dactech.booksmanagement.infrastructure.utilies.MessageCode;
import org.springframework.http.ResponseEntity;

import static dev.dactech.booksmanagement.infrastructure.utilies.MessageCode.SUCCESS;

public class ResponseFactory {
    public static ResponseEntity<ResponseBase> response(MessageCode messageCode){
        ResponseBase response = ResponseBase.builder()
                .messageCode(messageCode)
                .body(null)
                .build();
        return ResponseEntity.ok(response);
    }
    public static <T> ResponseEntity<ResponseBase> response(MessageCode messageCode, T body){
        ResponseBase response = ResponseBase.builder()
                .messageCode(messageCode)
                .body(body)
                .build();
        return ResponseEntity.ok(response);
    }
    public static ResponseEntity<ResponseBase> creationResponse(){
        return response(SUCCESS);
    }


}
