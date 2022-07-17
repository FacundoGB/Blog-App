package com.spring.blogapp.service;

import com.spring.blogapp.dto.PublicationDto;
import com.spring.blogapp.entity.Publication;
import com.spring.blogapp.exceptions.ResourceNotFoundException;
import com.spring.blogapp.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationServiceImp implements PublicationService{

    @Autowired
    private PublicationRepository repository;

    @Override
    public PublicationDto createPublication(PublicationDto dtopublication) {

        Publication publication = mapEntity(dtopublication);
        Publication newPublication = repository.save(publication);

        //we return the object as a dto
        PublicationDto response = mapDto(newPublication);
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
    @Override
    public List<PublicationDto> listAllPublications() {
        List<Publication> listOfPublications = repository.findAll();
        /*
        to the list we recover from the DB we add it in a stream flow
        and we are mapping it, in which we indicate that the object recieved from
        the list (publicaiton) we will map it into a DTO and show it as a list.
         */
        return listOfPublications.stream().map(publication -> mapDto(publication)).collect(Collectors.toList());

    }

    @Override
    public PublicationDto showPublicationById(long id) {
        Publication publication = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));
        return mapDto(publication);
        /*
        We're telling it to search a publication by id
        if not found we throw our class that has predetermined a NotFoundException
        and indicate its parameters.
         */
    }

    @Override
    public PublicationDto modifyPublication(PublicationDto dtopublication, long id) {
        Publication publication = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));

        publication.setTitle(dtopublication.getTitle());
        publication.setDescription(dtopublication.getDescription());
        publication.setContent(dtopublication.getContent());

        Publication modifiedPublication = repository.save(publication);

        return mapDto(modifiedPublication);
    }

    @Override
    public void deletePublication(long id) {
        Publication publication = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));

        repository.delete(publication);
    }

    //In this method we convert entity to DTO
    private PublicationDto mapDto(Publication publication) {
        PublicationDto dtopublication = new PublicationDto();

        /*
        To this transfer object DTO we are converting it to a publication entity
         Once done we return the dtopublication.
         */
        dtopublication.setId(publication.getId());
        dtopublication.setTitle(publication.getTitle());
        dtopublication.setDescription(publication.getDescription());
        dtopublication.setContent(publication.getContent());

        return dtopublication;
    }

    //In this method we convert DTO to Entity
    private Publication mapEntity(PublicationDto dtopublication) {

        Publication publication = new Publication();

        publication.setId(dtopublication.getId());
        publication.setTitle(dtopublication.getTitle());
        publication.setDescription(dtopublication.getDescription());
        publication.setContent(dtopublication.getContent());

        return publication;
    }
}

