package com.spring.blogapp.entity;

import lombok.*;

import javax.persistence.*;

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


}

/**
 * @Table(name = "publications", uniqueConstraints = {@UniqueConstrai
 * When we start the project the name of the table is publications
 * and it has a constraint that will be that there cant be a publication with the same title
 */