package com.spring.blogapp.service;

import com.spring.blogapp.dto.PublicationDto;

import java.util.List;

public interface PublicationService {

    public PublicationDto createPublication(PublicationDto dtopublication);

    public List<PublicationDto> listAllPublications();

    public PublicationDto showPublicationById(long id);

    public PublicationDto modifyPublication(PublicationDto dtopublication, long id);

    public void deletePublication (long id);
}
