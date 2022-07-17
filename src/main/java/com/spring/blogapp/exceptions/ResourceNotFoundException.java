package com.spring.blogapp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter @Setter
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private long fieldId;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldId) {
        super(String.format("%s Not found with : %s : '%s'", resourceName, fieldName, fieldId));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldId = fieldId;
    }
}
