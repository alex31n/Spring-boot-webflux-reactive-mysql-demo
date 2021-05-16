package com.example.reactivemysqldemo.controller;

import com.example.reactivemysqldemo.dto.AuthorDTO;
import com.example.reactivemysqldemo.model.Author;
import com.example.reactivemysqldemo.model.Book;
import com.example.reactivemysqldemo.repository.AuthorRepository;
import com.example.reactivemysqldemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "authors")
public class AuthorController {

    @Autowired
    AuthorRepository repository;

    @Autowired
    BookRepository bookRepository;

    @GetMapping
    public Flux<Author> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}/hierarchy")
    public Mono<AuthorDTO> findByIdWithBook(@PathVariable long id) {
        return repository.findById(id)
                .map(author -> {
                    AuthorDTO authorDTO = new AuthorDTO();
                    authorDTO.setId(author.getId());
                    authorDTO.setName(author.getName());
                    return authorDTO;
                })
                .flatMap(authorDTO -> Mono.just(authorDTO)
                        .zipWith(bookRepository.findAllByAuthorId(authorDTO.getId()).collectList())
                        .map(objects -> {
                            objects.getT1().setBooks(objects.getT2());
                            return objects.getT1();
                        })
                )
                .switchIfEmpty(Mono.error(new RuntimeException("Author not found")));
    }

    @GetMapping("/{id}")
    public Mono<Author> findById(@PathVariable long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Author not found")));
    }

    @PostMapping
    public Mono<Author> create(@RequestBody Author author) {
        return repository.save(author);
    }

    @PutMapping("/{id}")
    public Mono<Author> update(@PathVariable long id, @RequestBody Author author) {
        author.setId(id);
        return repository.save(author);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable long id) {
        return repository.findById(id)
                .flatMap(author -> repository.delete(author));
    }


}
