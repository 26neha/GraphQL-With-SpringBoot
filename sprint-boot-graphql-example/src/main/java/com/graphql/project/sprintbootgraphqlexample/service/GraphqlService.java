package com.graphql.project.sprintbootgraphqlexample.service;

import com.graphql.project.sprintbootgraphqlexample.model.Book;
import com.graphql.project.sprintbootgraphqlexample.repository.BookRepository;
import com.graphql.project.sprintbootgraphqlexample.service.datafetcher.AllBooksDataFetcher;
import com.graphql.project.sprintbootgraphqlexample.service.datafetcher.BookDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Service
public class GraphqlService {
    @Autowired
    BookRepository bookRepository;
    @Value("classpath:books.graphql")
    Resource resource;

    private GraphQL graphQL;
    @Autowired
    private AllBooksDataFetcher allBooksDataFetcher;
    @Autowired
    private BookDataFetcher bookDataFetcher;

    //load schema at application start up
    @PostConstruct
    private void loadSchema() throws IOException {
        //load Books into the Book repository
        loadDataIntoHSQL();
        //get the schema
        File schemaFile = resource.getFile();
        //parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private void loadDataIntoHSQL() {
        Stream.of(
                new Book("123", "Books of Clouds", "Kindle Edition", new String[]{
                        "Chloe Aridjis"
                }, "Nov 2017"),
                new Book("123", "Cloud Arch & Engineering", "Orielly", new String[]{
                        "Peter"
                }, "April 2002"),
                new Book("123", "Cloud Computing", "Kindle", new String[]{
                        "Venkat"
                }, "Nov 2017")

        ).forEach(book -> {
            bookRepository.save(book);
        });
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allBooks", allBooksDataFetcher)
                        .dataFetcher("book", bookDataFetcher))
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
