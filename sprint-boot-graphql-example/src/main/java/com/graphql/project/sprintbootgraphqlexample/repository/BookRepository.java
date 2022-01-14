package com.graphql.project.sprintbootgraphqlexample.repository;

import com.graphql.project.sprintbootgraphqlexample.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {


}
