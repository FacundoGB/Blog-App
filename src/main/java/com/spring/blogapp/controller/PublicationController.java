package com.spring.blogapp.controller;

import com.spring.blogapp.dto.PublicationDto;
import com.spring.blogapp.entity.Publication;
import com.spring.blogapp.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/publications")
public class PublicationController {

    @Autowired
    PublicationService service;

    @PostMapping
    public ResponseEntity<PublicationDto> savePublication(@RequestBody PublicationDto publicDto){
        return  new ResponseEntity<>(service.createPublication(publicDto), HttpStatus.CREATED);

    }

    @GetMapping
    public List<PublicationDto> listAllPublications() {
        return service.listAllPublications();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationDto> obtainPublicationById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(service.showPublicationById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicationDto> modifyPublication(@RequestBody PublicationDto publicDto, @PathVariable(name = "id") long id) {
        PublicationDto response = service.modifyPublication(publicDto, id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublication(@PathVariable(name = "id")long id) {
        service.deletePublication(id);
        return new ResponseEntity<>("Publication deleted successfully!", HttpStatus.OK);
    }
}


