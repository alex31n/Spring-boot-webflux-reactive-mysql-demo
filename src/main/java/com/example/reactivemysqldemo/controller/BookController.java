package com.example.reactivemysqldemo.controller;

import com.example.reactivemysqldemo.model.Book;
import com.example.reactivemysqldemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping
    public Flux<Book> findAll(){
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Book> findById(@PathVariable long id){
        return bookRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Book not found")));
    }

    @PostMapping
    public Mono<Book> create(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public Mono<Book> update(@PathVariable long id, @RequestBody Book book){
        book.setId(id);
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable long id){
        return findById(id)
                .flatMap(book -> bookRepository.delete(book));
    }



}
