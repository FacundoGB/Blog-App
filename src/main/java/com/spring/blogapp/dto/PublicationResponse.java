package com.spring.blogapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class PublicationResponse {

    private List<PublicationDto> content;
    private int numberOfPage;
    private int sizeOfPage;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
