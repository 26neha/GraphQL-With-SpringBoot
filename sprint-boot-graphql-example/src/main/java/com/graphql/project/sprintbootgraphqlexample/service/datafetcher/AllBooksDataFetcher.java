package com.graphql.project.sprintbootgraphqlexample.service.datafetcher;

import com.graphql.project.sprintbootgraphqlexample.model.Book;
import com.graphql.project.sprintbootgraphqlexample.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllBooksDataFetcher implements DataFetcher<List<Book>> {
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> get(DataFetchingEnvironment dataFetchingEnvironment)
    {
        return bookRepository.findAll();
    }
}
