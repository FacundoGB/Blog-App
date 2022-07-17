package com.spring.blogapp.service;

import com.spring.blogapp.dto.PublicationDto;
import com.spring.blogapp.entity.Publication;
import com.spring.blogapp.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationServiceImp implements PublicationService{

    @Autowired
    private PublicationRepository repository;

    @Override
    public PublicationDto createPublication(PublicationDto publicationDTO) {
        // We convert from DTO to Entity
        Publication publication = new Publication();

        publication.setTitle(publicationDTO.getTitle());
        publication.setDescription(publicationDTO.getDescription());
        publication.setContent(publicationDTO.getContent());

        Publication newPublication = repository.save(publication);

        //We convert from entity to DTO
        PublicationDto response = new PublicationDto();
        response.setId(newPublication.getId());
        response.setTitle(newPublication.getTitle());
        response.setDescription(newPublication.getDescription());
        response.setContent(newPublication.getContent());


        return response;
    }
    /**
     * We've indicated that it's a Service & injected repo.
     * We've created a publication. Postamn client will go to smth like "/createPublication"
     * done that we've tu transfer objects to it. We transform that DTO to an Entity
     * we transform the JSO into an Entity to be saved in an object. Once saved we persist it in the DB.
     *
     * Once persisted we give as response that Entity trasnformed back into JSON
     *
     */
}
