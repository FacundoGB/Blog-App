package com.spring.blogapp.exceptions;

import com.spring.blogapp.dto.DetailError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /*
    This annotation manages exceptions that have been detailed
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<DetailError> manageResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        DetailError detailError = new DetailError(new Date(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(detailError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogAppException.class)
    public ResponseEntity<DetailError> BlogAppException(BlogAppException exception, WebRequest webRequest) {
        DetailError detailError = new DetailError(new Date(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(detailError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DetailError> manageGlobalException(Exception exception, WebRequest webRequest) {
        DetailError detailError = new DetailError(new Date(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(detailError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
    this method handles not valid entries
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> allErrors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nameBody = ((FieldError)error).getField();
            String message = error.getDefaultMessage();

            allErrors.put(nameBody, message);
        });
        return new ResponseEntity<>(allErrors,HttpStatus.BAD_REQUEST);

    }
}
