package com.spring.blogapp.exceptions;

import com.spring.blogapp.dto.DetailError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
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
}
