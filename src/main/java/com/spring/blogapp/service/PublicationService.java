package com.spring.blogapp.service;

import com.spring.blogapp.dto.PublicationDto;
import com.spring.blogapp.dto.PublicationResponse;

import java.util.List;

public interface PublicationService {

    public PublicationDto createPublication(PublicationDto publicationDto);

    public PublicationResponse listAllPublications(int pageN, int pageS, String sortBy, String sortDir);

    public PublicationDto showPublicationById(long id);

    public PublicationDto modifyPublication(PublicationDto publicationDto, long id);

    public void deletePublication (long id);
}
