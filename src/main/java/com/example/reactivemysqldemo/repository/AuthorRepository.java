package com.example.reactivemysqldemo.repository;

import com.example.reactivemysqldemo.model.Author;
import com.example.reactivemysqldemo.model.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AuthorRepository extends ReactiveCrudRepository<Author, Long> {
}
