package com.spring.blogapp.service;

import com.spring.blogapp.dto.PublicationDto;
import com.spring.blogapp.dto.PublicationResponse;
import com.spring.blogapp.entity.Publication;
import com.spring.blogapp.exceptions.ResourceNotFoundException;
import com.spring.blogapp.repository.PublicationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationServiceImp implements PublicationService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PublicationRepository repository;

    @Override
    public PublicationDto createPublication(PublicationDto publicationDto) {

        Publication publication = mapEntity(publicationDto);
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
    public PublicationResponse listAllPublications(int pageN, int pageS, String sortBy, String sortDir) {

        /**
         * We indicate first that we use Sort
         * if sortDir is equal to the ascending order and has a name, then we
         * sort it by the value we parse it ascending.
         * if not (:) we order it descending.
         */
        Sort dir = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageN,pageS,dir);
        Page<Publication> publications = repository.findAll(pageable);

        //We get the content of the page (with its constraints) and pass it to the whole list.
        List<Publication> listOfPublications = publications.getContent();
        /*
        to the list we recover from the DB we add it in a stream flow
        and we are mapping it, in which we indicate that the object recieved from
        the list (publicaiton) we will map it into a DTO and show it as a list.
         */
        List<PublicationDto> content = listOfPublications.stream().map(publication -> mapDto(publication)).collect(Collectors.toList());

        //once we have our list of publication dto with page number and its size
        //the response will have as content the list od dtos and the following characteristics
        PublicationResponse pubResp = new PublicationResponse();
        pubResp.setContent(content);
        pubResp.setNumberOfPage(publications.getNumber());
        pubResp.setSizeOfPage(publications.getSize());
        pubResp.setTotalElements(publications.getTotalElements());
        pubResp.setTotalPages(publications.getTotalPages());
        pubResp.setLast(publications.isLast());

        return pubResp;

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
    public PublicationDto modifyPublication(PublicationDto publicationDto, long id) {
        Publication publication = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publication", "id", id));

        publication.setTitle(publicationDto.getTitle());
        publication.setDescription(publicationDto.getDescription());
        publication.setContent(publicationDto.getContent());

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
        //we remove this mapping method to replace it with modelmapper
        //we map the publication object to its destination type: publication dto class
        PublicationDto publicationDto = modelMapper.map(publication, PublicationDto.class);
        return publicationDto;
    }

    //In this method we convert DTO to Entity
    private Publication mapEntity(PublicationDto publicationDto) {
        //we remove this mapping method to replace it with modelmapper
        //we map the dto object to its destination type: publication class
        Publication publication = modelMapper.map(publicationDto, Publication.class);
        return publication;
    }
}

