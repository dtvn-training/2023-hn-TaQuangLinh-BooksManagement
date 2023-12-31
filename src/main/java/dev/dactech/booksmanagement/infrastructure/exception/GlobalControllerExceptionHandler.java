package dev.dactech.booksmanagement.infrastructure.exception;

import dev.dactech.booksmanagement.infrastructure.dto.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler{
    private static final Logger LOGGER = LogManager.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Response> handleConversion(Exception ex) {
        LOGGER.error("ERROR: ", ex);
        Response response = new Response();
        response.setMessage(ex.getMessage());
        if (ex instanceof ApiException) {
            ApiException apiException = (ApiException) ex;
            response.setCode(apiException.getCode());
            response.setMessage(apiException.getMessage());
            response.setData(apiException.getData());
        } else {
            response.setCode(500);
            response.setMessage("System Error !");
        }
        return ResponseEntity.ok(response);
    }
}
