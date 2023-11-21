package dev.dactech.booksmanagement.app.service;

import dev.dactech.booksmanagement.infrastructure.dto.response.BaseResponse;
import dev.dactech.booksmanagement.infrastructure.dto.response.Response;
import dev.dactech.booksmanagement.infrastructure.dto.response.ResponseList;
import dev.dactech.booksmanagement.infrastructure.utilies.MessageCode;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static dev.dactech.booksmanagement.infrastructure.utilies.MessageCode.SUCCESS;

public class ResponseFactory {

    public static <T> ResponseEntity<Response<T>> response(MessageCode messageCode, T data){
        Response response = new Response();
        response.setMessageCode(messageCode);
        response.setData(data);
        return ResponseEntity.ok(response);
    }
    public static ResponseEntity<BaseResponse> response(MessageCode messageCode){
        BaseResponse reponse = new BaseResponse(messageCode);
        return ResponseEntity.ok(reponse);
    }
    public static <T> ResponseEntity<Response<T>> response(T data){
        return response(SUCCESS, data);
    }
    public static ResponseEntity<BaseResponse> creationResponse(){
        return response(SUCCESS);
    }
    public static <T> ResponseEntity<ResponseList<T>> response(List<T> data){
        ResponseList<T> response = new ResponseList<>();
        response.setData(data);
        response.setMessageCode(SUCCESS);
        return ResponseEntity.ok(response);
    }


}
