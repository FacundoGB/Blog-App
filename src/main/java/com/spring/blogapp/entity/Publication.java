package com.spring.blogapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor @NoArgsConstructor @ToString
@Table(name = "publications", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
@Getter @Setter
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "content", nullable = false)
    private String content;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    //once a publication is removed all asociated data (comments) is removed aswell (orphanRemoval)
}

/**
 * @Table(name = "publications", uniqueConstraints = {@UniqueConstrai
 * When we start the project the name of the table is publications
 * and it has a constraint that will be that there cant be a publication with the same title
 */