package com.graphql.project.sprintbootgraphqlexample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
@Entity

public class Book {
    @Id
    private String isn;
    private String title;
    private String publisher;
    private String[] authors;
    private String publishedDate;



}
