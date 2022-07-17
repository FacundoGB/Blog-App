package com.spring.blogapp.controller;

import com.spring.blogapp.dto.PublicationDto;
import com.spring.blogapp.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/publications")
public class PublicationController {

    @Autowired
    PublicationService service;

    @PostMapping
    public ResponseEntity<PublicationDto> savePublication(@RequestBody PublicationDto publicDto){
        return  new ResponseEntity<>(service.createPublication(publicDto), HttpStatus.CREATED);

    }
}
