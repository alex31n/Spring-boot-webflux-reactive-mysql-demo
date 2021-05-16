package com.example.reactivemysqldemo.repository;

import com.example.reactivemysqldemo.model.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface BookRepository extends ReactiveCrudRepository<Book, Long> {

    Flux<Book> findAllByAuthorId(long authorId);
}
