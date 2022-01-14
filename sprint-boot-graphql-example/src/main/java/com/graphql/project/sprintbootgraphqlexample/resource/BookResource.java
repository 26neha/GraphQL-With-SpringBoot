package com.graphql.project.sprintbootgraphqlexample.resource;

import com.graphql.project.sprintbootgraphqlexample.service.GraphqlService;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rest/books")
@RestController
public class BookResource {
    @Autowired
    GraphqlService graphqlService;

    @PostMapping
    public ResponseEntity<Object> getAllBooks(@RequestBody String query) {
        ExecutionResult execute = graphqlService.getGraphQL().execute(query);
        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
}
