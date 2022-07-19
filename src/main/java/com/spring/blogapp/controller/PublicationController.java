package com.spring.blogapp.controller;

import com.spring.blogapp.dto.PublicationDto;
import com.spring.blogapp.dto.PublicationResponse;
import com.spring.blogapp.service.PublicationService;
import com.spring.blogapp.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/publications")
public class PublicationController {

    @Autowired
    private PublicationService service;

    @GetMapping
    public PublicationResponse listAllPublications(@RequestParam(value = "pageNo", defaultValue = AppConstants.NUMBER_OF_PAGE_DEFAULT, required = false) int pageN,
                                                   @RequestParam(value = "pageSize", defaultValue = AppConstants.SIZE_OF_PAGE_DEFAULT, required = false) int pageS,
                                                   @RequestParam(value = "sortBy", defaultValue = AppConstants.ORDER_BY_DEFECT_DEFAULT, required = false) String sortBy,
                                                   @RequestParam(value = "sortDir", defaultValue = AppConstants.ORDER_BY_DIRECTION_DEFAULT, required = false) String sortDir) {
        return service.listAllPublications(pageN,pageS,sortBy,sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationDto> obtainPublicationById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(service.showPublicationById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PublicationDto> savePublication(@Valid @RequestBody PublicationDto publicDto){
        return  new ResponseEntity<>(service.createPublication(publicDto), HttpStatus.CREATED);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PublicationDto> modifyPublication(@Valid @RequestBody PublicationDto publicDto, @PathVariable(name = "id") long id) {
        PublicationDto response = service.modifyPublication(publicDto, id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublication(@PathVariable(name = "id")long id) {
        service.deletePublication(id);
        return new ResponseEntity<>("Publication deleted successfully!", HttpStatus.OK);
    }
}


