package org.example.market_place.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    //handle all the constriction inside the DTO
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        StringBuilder errorDetails = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->  {
            errorDetails.append(fieldError.getField());
            errorDetails.append(": ");
            errorDetails.append(fieldError.getDefaultMessage());
            errorDetails.append("\n");
        });

        ErrorDto dto = new ErrorDto(HttpStatus.BAD_REQUEST.value(), errorDetails.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    @ExceptionHandler({DataDuplicateException.class,DataNotFoundException.class, AuthenticationException.class,IllegalArgumentException.class})
    public ResponseEntity<ErrorDto> handleEmailException(Exception e){
        ErrorDto dto = new ErrorDto(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorDto> handleGlobalException(Exception e){
        String uuid = UUID.randomUUID().toString();
        System.out.println("--------------------");
        System.out.println("ERROR ID: " + uuid);
        e.printStackTrace();
        System.out.println("--------------------");
        ErrorDto dto = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Call the support team.");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);


    }
}
